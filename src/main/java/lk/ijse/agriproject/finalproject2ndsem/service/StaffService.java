package lk.ijse.agriproject.finalproject2ndsem.service;



import lk.ijse.agriproject.finalproject2ndsem.customObj.StaffResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;


import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void updateStaff(StaffDTO staffDTO);
    void deleteStaff(String id);
    List<StaffDTO> getAllStaff();
    StaffResponse getSelectStaffById(String id);

}
