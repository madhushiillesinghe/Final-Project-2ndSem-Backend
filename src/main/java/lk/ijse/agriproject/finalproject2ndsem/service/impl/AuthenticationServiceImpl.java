package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import lk.ijse.agriproject.finalproject2ndsem.dao.UserDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.UserEntity;
import lk.ijse.agriproject.finalproject2ndsem.jwtmodels.JwtAuthResponse;
import lk.ijse.agriproject.finalproject2ndsem.jwtmodels.SignIn;
import lk.ijse.agriproject.finalproject2ndsem.service.AuthenticationService;
import lk.ijse.agriproject.finalproject2ndsem.service.JWTService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDAO userRepository;
    private final JWTService jwtService;
    private final Mapping mapping;
    //utils
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userRepository.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JwtAuthResponse.builder().token(generatedToken).build() ;
    }

    @Override
    public JwtAuthResponse signUp(UserDTO signUpUser) {
        System.out.println(signUpUser+"sign up user");
        UserEntity userEntity = mapping.convertToUserEntity(signUpUser);
        var savedUser = userRepository.save(userEntity);
        var genToken = jwtService.generateToken(savedUser);
        return JwtAuthResponse.builder().token(genToken).build();
    }

    @Override
    public JwtAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUsername(accessToken);
        var userEntity =
                userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JwtAuthResponse.builder().token(refreshToken).build();
    }
}