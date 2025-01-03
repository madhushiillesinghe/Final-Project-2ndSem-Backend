package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffId",nullable = false)
    @JsonIgnore
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name = "field_code", referencedColumnName = "fieldCode")
    @JsonIgnore
    private FieldEntity field;
}

