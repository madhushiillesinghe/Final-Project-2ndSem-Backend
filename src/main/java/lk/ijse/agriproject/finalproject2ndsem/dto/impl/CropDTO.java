package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import jakarta.persistence.Column;
import lk.ijse.agriproject.finalproject2ndsem.customObj.Response;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.FieldEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.MoniteringLogEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperEntity, Response {
    private String code;
    private String commonName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String season;
    private String fieldCode;
    private List<MoniteringLogEntity> logs;

}