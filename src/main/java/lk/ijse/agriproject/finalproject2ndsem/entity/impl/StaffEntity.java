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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "staff")
@Entity
public class StaffEntity implements SuperEntity {
    @Id
    private String id;
    private Name name;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date joined_date;
    private Date dob;
    private Address address;
    private String contact_no;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EquipmentEntity equipments;

    @ManyToMany(mappedBy = "staff")
    private List<MoniteringLogEntity> moniteringLogEntities = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<VehicalEntity> vehicles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    )
    private List<FieldEntity> fields = new ArrayList<>();
}
