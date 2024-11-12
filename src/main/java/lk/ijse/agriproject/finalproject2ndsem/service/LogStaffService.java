package lk.ijse.agriproject.finalproject2ndsem.service;

import lk.ijse.agriproject.finalproject2ndsem.dto.impl.MoniteringLogDTO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;

import java.util.List;

public interface LogStaffService {
    void updateMoniteringLogStaff(String id, List<StaffDTO> staffDTOS);

}
