package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Role;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.jwtmodels.JwtAuthResponse;
import lk.ijse.agriproject.finalproject2ndsem.jwtmodels.SignIn;
import lk.ijse.agriproject.finalproject2ndsem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

//    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<JwtAuthResponse> signUp(
//            @RequestPart("email") String email,
//            @RequestPart("password") String password,
//            @RequestPart("role") String role) {
//        try {
//            // Handle profile picture
//            // Build the user object
//            UserDTO buildUserDTO = new UserDTO();
//            buildUserDTO.setEmail(email);
//            buildUserDTO.setPassword(passwordEncoder.encode(password));
//            buildUserDTO.setRole(Role.valueOf(role));
//            // Send to the service layer
//            return ResponseEntity.ok(authenticationService.signUp(buildUserDTO));
//        } catch (DataPersistFailedException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
@PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<JwtAuthResponse> signUp(
        @RequestPart("email") String email,
        @RequestPart("password") String password,
        @RequestPart("role") String role) {
        try {
            // Check if the email does not exist
            if (authenticationService.getSelectStaffById(email) != null) {
                // Build the UserDTO object with encoded password
                UserDTO buildUserDTO = new UserDTO();
                buildUserDTO.setEmail(email);
                buildUserDTO.setPassword(passwordEncoder.encode(password));
                buildUserDTO.setRole(Role.valueOf(role.toUpperCase()));

                // Send UserDTO to the service layer to complete sign-up
                JwtAuthResponse response = authenticationService.signUp(buildUserDTO);

                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT); // Email already exists
            }

        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

    @PostMapping(value = "/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn sign) {
        return ResponseEntity.ok(authenticationService.signIn(sign));
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}