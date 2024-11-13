package lk.ijse.agriproject.finalproject2ndsem.service;

import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import lk.ijse.agriproject.finalproject2ndsem.jwtmodels.JwtAuthResponse;
import lk.ijse.agriproject.finalproject2ndsem.jwtmodels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);
}