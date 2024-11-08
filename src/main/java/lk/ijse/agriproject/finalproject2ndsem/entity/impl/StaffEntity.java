package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import jakarta.persistence.*;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Address;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Gender;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Name;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Role;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String id;
    private Name name;
    private String designation;
    private Gender gender;
    private Date joined_date;
    private Date dob;
    private Address address;
    private String contact_no;
    @Column(unique = true)
    private String email;
    private Role role;
    @OneToOne(mappedBy = "staff")
    private EquipmentEntity equipments;





}
