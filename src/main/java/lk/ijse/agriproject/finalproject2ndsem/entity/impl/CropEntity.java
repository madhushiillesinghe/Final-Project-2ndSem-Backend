package lk.ijse.agriproject.finalproject2ndsem.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String commonName;
    private String scientificName;
    @Column(name = "crop_image",columnDefinition = "LONGTEXT")
    private String cropImage;
    private String category;
    private String season;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "field_code", referencedColumnName = "fieldCode")
    private FieldEntity field;

    @ManyToMany(mappedBy = "crops",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MoniteringLogEntity> monitoringLogs = new ArrayList<>();
}