package lk.ijse.agriproject.finalproject2ndsem.service;



import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(String id,UserDTO userDTO);
    void deleteUser(String userEmail);
    List<UserDTO> getAllUsers();
    UserResponse getSelectUserByEmail(String userEmail);
    UserDetailsService userDetailsService();

}
