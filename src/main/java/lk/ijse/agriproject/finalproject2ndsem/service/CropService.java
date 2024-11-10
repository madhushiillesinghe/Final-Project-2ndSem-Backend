package lk.ijse.agriproject.finalproject2ndsem.service;


import lk.ijse.agriproject.finalproject2ndsem.customObj.CropResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.StaffResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.CropDTO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    void updateCrop(String code,CropDTO cropDTO);
    void deleteCrop(String code);
    List<CropDTO> getAllCrops();
    CropResponse getSelectCropByCode(String code);

}
