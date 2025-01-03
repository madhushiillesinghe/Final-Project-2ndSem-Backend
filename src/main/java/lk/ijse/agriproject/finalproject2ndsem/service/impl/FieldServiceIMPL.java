package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.FieldResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.FieldErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.FieldDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.FieldDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.FieldEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.FieldNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.FieldService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity fieldsave= fieldDAO.save(mapping.convertToFieldEntity(fieldDTO));
        if(fieldsave == null && fieldsave.getFieldCode() == null ) {
            throw new DataPersistFailedException("Cannot saved Field");
        }
    }

    @Override
    public void updateField(String code,FieldDTO fieldDTO) {
        Optional<FieldEntity> updateByCode=fieldDAO.findById(code);
        if(!updateByCode.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else {
            updateByCode.get().setFieldImage2(fieldDTO.getFieldImage2());
            updateByCode.get().setFieldImage1(fieldDTO.getFieldImage1());
            updateByCode.get().setFieldName(fieldDTO.getFieldName());
            updateByCode.get().setFieldLocation(fieldDTO.getFieldLocation());
            updateByCode.get().setExtentSize(fieldDTO.getExtentSize());

        }
    }

    @Override
    public void deleteField(String code) {
        Optional<FieldEntity> selectedFieldCode = fieldDAO.findById(code);
        if(selectedFieldCode.isPresent()){
            fieldDAO.deleteById(code);
        }else {
            throw new FieldNotFoundException("Field not found");
        }
    }
    @Override
    public List<FieldDTO> getAllFields() {
        return  mapping.convertToFieldDTO(fieldDAO.findAll());
    }
    @Override
    public FieldResponse getSelectFieldByCode(String code) {
        if(fieldDAO.existsById(code)){
            Optional<FieldEntity> fieldEntityByCode = fieldDAO.findById(code);
            return mapping.convertToFieldDTO(fieldEntityByCode.get());
        }else {
            return new FieldErrorResponse(0, "Field not found");
        }
    }
}
