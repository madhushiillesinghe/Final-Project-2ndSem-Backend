package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import jakarta.persistence.*;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {

    @Id
    private String field_code;
    private String field_name;
    private Point field_location;
    private double extent_size;

    @Column(columnDefinition = "LONGTEXT")
    private String field_image1;

    @Column(columnDefinition = "LONGTEXT")
    private String field_image2;

    @OneToMany(mappedBy = "field")
    private List<CropEntity> crops = new ArrayList<>();

    @OneToMany(mappedBy = "field")
    private List<MoniteringLogEntity> monitoringLogs = new ArrayList<>();

    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EquipmentEntity equipments;

    @ManyToMany(mappedBy = "fields")
    private List<StaffEntity> staffEntityList = new ArrayList<>();
}
