package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.VehicleResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicle_code;
    private String license_plate_no;
    private String vehicle_category;
    private String fuel_type;
    private String status;
    private String remarks;
    private String staff_id; // This corresponds to the StaffEntity's ID

}
