package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.MoniteringLogResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.CropEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoniteringLogDTO implements SuperDTO, MoniteringLogResponse {
    private String logCode;
    private Date logDate;
    private String observation;
    private String fieldCode;
    private String observedImage;
    private List<CropEntity> crops;
    private List<StaffEntity> staff;


}
