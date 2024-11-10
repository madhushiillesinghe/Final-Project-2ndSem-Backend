package lk.ijse.agriproject.finalproject2ndsem.service;



import lk.ijse.agriproject.finalproject2ndsem.customObj.MoniteringLogResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.MoniteringLogDTO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;

import java.util.List;

public interface MoniteringLogService {
    void saveMoniteringLog(MoniteringLogDTO moniteringLogDTO);
    void updateMoniteringLog(String id,MoniteringLogDTO moniteringLogDTO);
    void deleteMoniteringLog(String id);
    List<MoniteringLogDTO> getAllMoniteringLogs();
    MoniteringLogResponse getSelectMoniteringLogById(String id);

}
