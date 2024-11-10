package lk.ijse.agriproject.finalproject2ndsem.entity.impl;
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
        private String log_code;
        private Date log_date;
        private String observation;
        @Column(name = "observed_image",columnDefinition = "LONGTEXT")
        private String observed_image;

        @ManyToOne
        @JoinColumn(name = "field_code",referencedColumnName = "field_code")
        private FieldEntity field;

        @ManyToMany
        @JoinTable(
                name = "crop_monitering_log",
                joinColumns = @JoinColumn(name = "log_code",referencedColumnName = "log_code"),
                inverseJoinColumns = @JoinColumn(name = "crop_code",referencedColumnName = "code")
        )
        private List<CropEntity> crops = new ArrayList<>();

        @ManyToMany
        @JoinTable(
                name = "monitoring_log_staff",
                joinColumns = @JoinColumn(name = "log_code",referencedColumnName = "log_code"),
                inverseJoinColumns = @JoinColumn(name = "staff_id",referencedColumnName = "id")
        )
        private List<StaffEntity> staff = new ArrayList<>();
    }
