package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.StaffResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Address;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Gender;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Name;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Role;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.EquipmentEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.FieldEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.MoniteringLogEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.VehicalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String id;
    private Name name;
    private String designation;
    private Gender gender;
    private Date joined_date;
    private Date dob;
    private Address address;
    private String contact_no;
    private String email;
    private Role role;
    private EquipmentEntity equipment;
    private List<MoniteringLogEntity> moniteringLogs;
    private List<VehicalEntity> vehicles;
    private List<FieldEntity> fields;
}
