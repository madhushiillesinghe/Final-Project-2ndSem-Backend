package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.CropResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.CropErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.CropDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.FieldDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.StaffDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.CropDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.CropEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.FieldEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.CropNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.CropService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceIMPL implements CropService {
    @Autowired
    private CropDAO cropDao;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropsave= cropDao.save(mapping.convertToCropEntity(cropDTO));
        if(cropsave == null && cropsave.getCode() == null ) {
            throw new DataPersistFailedException("Cannot saved Crop");
        }
    }
    @Override
    public void updateCrop(String code,CropDTO cropDTO) {
        Optional<CropEntity> updateByCode=cropDao.findById(code);
        Optional<FieldEntity> fieldDAOById = fieldDAO.findById(cropDTO.getFieldCode());

        if(!updateByCode.isPresent()){
            throw new CropNotFoundException("Crop not found");
        }else {
            updateByCode.get().setCategory(cropDTO.getCategory());
            updateByCode.get().setCropImage(cropDTO.getCropImage());
            updateByCode.get().setSeason(cropDTO.getSeason());
            updateByCode.get().setScientificName(cropDTO.getScientificName());
            updateByCode.get().setCommonName(cropDTO.getCommonName());
            updateByCode.get().setField(fieldDAOById.get());
        }
    }

    @Override
    public void deleteCrop(String code) {
        Optional<CropEntity> selectedCropCode = cropDao.findById(code);
        if(selectedCropCode.isPresent()){
            cropDao.deleteById(code);
        }else {
            throw new CropNotFoundException("Crop not found");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return  mapping.convertToCropDTO(cropDao.findAll());
    }

    @Override
    public CropResponse getSelectCropByCode(String code) {
        if(cropDao.existsById(code)){
            Optional<CropEntity> cropEntityByCode = cropDao.findById(code);
            return mapping.convertToCropDTO(cropEntityByCode.get());
        }else {
            return new CropErrorResponse(0, "crop not found");
        }    }
}
