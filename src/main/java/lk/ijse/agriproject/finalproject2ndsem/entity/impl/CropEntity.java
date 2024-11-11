package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import jakarta.persistence.*;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {
    @Id
    private String code;
    private String common_name;
    private String scientific_name;
    @Column(columnDefinition = "LONGTEXT")
    private String crop_image;
    private String category;
    private String season;
    @ManyToOne
    @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    private FieldEntity field;

    @ManyToMany(mappedBy = "crops")
    private List<MoniteringLogEntity> monitoringLogs = new ArrayList<>();
}