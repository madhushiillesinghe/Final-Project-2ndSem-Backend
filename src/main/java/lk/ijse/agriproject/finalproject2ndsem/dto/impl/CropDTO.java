package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.CropResponse;
import lk.ijse.agriproject.finalproject2ndsem.entity.SuperEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.MoniteringLogEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperEntity, CropResponse {
    private String code;
    private String common_name;
    private String scientific_name;
    private String crop_image;
    private String category;
    private String season;
    private String field_code;
    private List<MoniteringLogEntity> logs;

}
