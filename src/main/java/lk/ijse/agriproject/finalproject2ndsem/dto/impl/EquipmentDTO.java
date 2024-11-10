package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.EquipmentResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String id;
    private String name;
    private String type;
    private String status;
    private String field_code;
    private String staff_id;
}
