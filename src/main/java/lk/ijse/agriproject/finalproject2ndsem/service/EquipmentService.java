package lk.ijse.agriproject.finalproject2ndsem.service;

import lk.ijse.agriproject.finalproject2ndsem.customObj.EquipmentResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(String code,EquipmentDTO equipmentDTO);
    void deleteEquipment(String code);
    List<EquipmentDTO> getAllEquipments();
    EquipmentResponse getSelectEquipmentByCode(String code);

}
