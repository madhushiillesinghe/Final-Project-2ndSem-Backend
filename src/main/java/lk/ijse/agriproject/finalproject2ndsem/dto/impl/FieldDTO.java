package lk.ijse.agriproject.finalproject2ndsem.dto.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.SuperDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.CropEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.MoniteringLogEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, UserResponse {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private String equipmentId;
    private List<StaffEntity> staffEntityList;
    private List<CropEntity> crops;
    private List<MoniteringLogEntity> monitoringLogs;

}
