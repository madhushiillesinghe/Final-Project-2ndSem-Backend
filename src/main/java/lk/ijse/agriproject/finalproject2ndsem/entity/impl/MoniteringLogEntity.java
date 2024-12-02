package lk.ijse.agriproject.finalproject2ndsem.entity.impl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
@Table(name = "monitoring_log")
public class MoniteringLogEntity implements SuperEntity {
        @Id
        private String logCode;
        private Date logDate;
        private String observation;
        @Column(name = "observed_image",columnDefinition = "LONGTEXT")
        private String observedImage;

        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "field_code",referencedColumnName = "fieldCode")
        private FieldEntity field;

        @ManyToMany
        @JsonIgnore
        @JoinTable(
                name = "crop_monitering_log",
                joinColumns = @JoinColumn(name = "log_code",referencedColumnName = "logCode"),
                inverseJoinColumns = @JoinColumn(name = "crop_code",referencedColumnName = "code")
        )
        private List<CropEntity> crops = new ArrayList<>();

        @ManyToMany
        @JsonIgnore
        @JoinTable(
                name = "monitoring_log_staff",
                joinColumns = @JoinColumn(name = "log_code",referencedColumnName = "logCode"),
                inverseJoinColumns = @JoinColumn(name = "staff_id",referencedColumnName = "id")
        )
        private List<StaffEntity> staff = new ArrayList<>();
    }
