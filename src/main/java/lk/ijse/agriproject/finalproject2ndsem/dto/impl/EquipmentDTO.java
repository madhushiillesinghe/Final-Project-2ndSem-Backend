package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, UserResponse {
    private String id;
    private String name;
    private String type;
    private String status;
    private String fieldCode;
    private String staffId;
}
