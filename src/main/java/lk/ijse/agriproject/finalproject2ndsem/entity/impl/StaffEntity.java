package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "join_date")
    private Date joinedDate;
    private Date dob;
    private Address address;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(unique = true,nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EquipmentEntity >equipment=new ArrayList<>();

    @ManyToMany(mappedBy = "staff",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MoniteringLogEntity> moniteringLogEntities = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VehicalEntity> vehicles = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "field_code", referencedColumnName = "fieldCode")
    )
    private List<FieldEntity> fields = new ArrayList<>();
}
