package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.Response;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO  implements SuperDTO, Response {
    private String email;
    private String password;
    private Role role;
}
