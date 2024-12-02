package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.CropResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.EquipmentResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.CropErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.EquipmentErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.CropDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.EquipmentDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.FieldDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.StaffDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.CropDTO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.EquipmentDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.CropEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.EquipmentEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.FieldEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.CropNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.EquipmentNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.CropService;
import lk.ijse.agriproject.finalproject2ndsem.service.EquipmentService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        EquipmentEntity equipmentsave= equipmentDAO.save(mapping.convertToEquipmentEntity(equipmentDTO));
        if(equipmentsave == null && equipmentsave.getId() == null ) {
            throw new DataPersistFailedException("Cannot saved Equipment");
        }
    }

    @Override
    public void updateEquipment(String code,EquipmentDTO equipmentDTO) {
        Optional<StaffEntity> staffDAOById = staffDAO.findById(equipmentDTO.getStaffId());
        Optional<FieldEntity> fieldDAOById = fieldDAO.findById(equipmentDTO.getFieldCode());
        Optional<EquipmentEntity> updateByCode=equipmentDAO.findById(code);
        if(!updateByCode.isPresent()){
            throw new EquipmentNotFoundException("Equipment not found");
        }else {
            updateByCode.get().setName(equipmentDTO.getName());
            updateByCode.get().setType(equipmentDTO.getType());
            updateByCode.get().setStatus(equipmentDTO.getStatus());
            updateByCode.get().setField(fieldDAOById.get());
            updateByCode.get().setStaff(staffDAOById.get());

        }
    }
    @Override
    public void deleteEquipment(String code) {
        Optional<EquipmentEntity> selectedEquipmentCode = equipmentDAO.findById(code);
        if(selectedEquipmentCode.isPresent()){
            equipmentDAO.deleteById(code);
        }else {
            throw new EquipmentNotFoundException("Equipment not found");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return  mapping.convertToEquipmentDTO(equipmentDAO.findAll());
    }

    @Override
    public EquipmentResponse getSelectEquipmentByCode(String code) {
        if(equipmentDAO.existsById(code)){
            Optional<EquipmentEntity> equipmentEntityByCode = equipmentDAO.findById(code);
            return mapping.convertToEquipmentDTO(equipmentEntityByCode.get());
        }else {
            return new EquipmentErrorResponse(0, "equipment not found");
        }
    }
}
