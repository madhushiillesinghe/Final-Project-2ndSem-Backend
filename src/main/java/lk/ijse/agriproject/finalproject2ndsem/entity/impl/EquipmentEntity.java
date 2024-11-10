package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import jakarta.persistence.*;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private String status;
    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    private FieldEntity field;
}

