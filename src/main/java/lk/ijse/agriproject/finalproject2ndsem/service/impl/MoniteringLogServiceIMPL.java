package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.MoniteringLogResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.MoniteringLogErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.UserErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.MoniteringLogDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.UserDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.MoniteringLogDTO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.MoniteringLogEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.UserEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.MoniteringLogNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.MoniteringLogService;
import lk.ijse.agriproject.finalproject2ndsem.service.UserService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MoniteringLogServiceIMPL implements MoniteringLogService {

    @Autowired
    private MoniteringLogDAO moniteringLogDAO;
    @Autowired
    private Mapping mapping;


    @Override
    public void saveMoniteringLog(MoniteringLogDTO moniteringLogDTO) {
        MoniteringLogEntity moniteringLogsave= moniteringLogDAO.save(mapping.convertToMoniteringLogEntity(moniteringLogDTO));
        if(moniteringLogsave == null && moniteringLogsave.getLogCode() == null ) {
            throw new DataPersistFailedException("Cannot saved MoniteringLog");
        }
    }

    @Override
    public void updateMoniteringLog(String code,MoniteringLogDTO moniteringLogDTO) {
        Optional<MoniteringLogEntity> updateById=moniteringLogDAO.findById(code);
        if(!updateById.isPresent()){
            throw new MoniteringLogNotFoundException("MoniteringLog not found");
        }else {
            updateById.get().setLogDate(moniteringLogDTO.getLogDate());
            updateById.get().setObservation(moniteringLogDTO.getObservation());
            updateById.get().setObservedImage(moniteringLogDTO.getObservedImage());
            updateById.get().setCrops(moniteringLogDTO.getCrops());
            updateById.get().setStaff(moniteringLogDTO.getStaff());
        }
    }

    @Override
    public void deleteMoniteringLog(String id) {
        Optional<MoniteringLogEntity> selectedLogId = moniteringLogDAO.findById(id);
        if(selectedLogId.isPresent()){
            moniteringLogDAO.deleteById(id);
        }else {
            throw new MoniteringLogNotFoundException("MoniteringLog not found");
        }
    }

    @Override
    public List<MoniteringLogDTO> getAllMoniteringLogs() {
        return  mapping.convertToMoniteringLogDTO(moniteringLogDAO.findAll());

    }
    @Override
    public MoniteringLogResponse getSelectMoniteringLogById(String id) {
        if(moniteringLogDAO.existsById(id)){
            Optional<MoniteringLogEntity> moniteringLogEntityById = moniteringLogDAO.findById(id);
            return mapping.convertToMoniteringLogDTO(moniteringLogEntityById.get());
        }else {
            return new MoniteringLogErrorResponse(0, "MoniteringLog not found");
        }
    }
}
