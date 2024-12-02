package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.awt.Point;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {

    @Id
    private String fieldCode;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_location")
    private Point fieldLocation;
    @Column(name = "extent_size")
    private double extentSize;
    @Column(name = "field_image1",columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Column(name = "field_image2",columnDefinition = "LONGTEXT")
    private String fieldImage2;

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CropEntity> crops = new ArrayList<>();

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MoniteringLogEntity> monitoringLogs = new ArrayList<>();

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EquipmentEntity> equipments;

    @ManyToMany(mappedBy = "fields",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StaffEntity> staffEntityList = new ArrayList<>();
}
