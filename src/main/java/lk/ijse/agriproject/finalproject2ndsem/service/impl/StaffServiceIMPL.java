package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.StaffResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.StaffErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.StaffDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.StaffNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.StaffService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        StaffEntity staffsave= staffDAO.save(mapping.convertToStaffEntity(staffDTO));
        if(staffsave == null && staffsave.getStaff_id() == null ) {
            throw new DataPersistFailedException("Cannot saved Staff Member");
        }
    }
    @Override
    public void updateStaff(String id,StaffDTO staffDTO) {
        Optional<StaffEntity> updateById=staffDAO.findById(id);
        if(!updateById.isPresent()){
            throw new StaffNotFoundException("Staff not found");
        }else {
            updateById.get().setEmail(staffDTO.getEmail());
            updateById.get().setRole(staffDTO.getRole());
            updateById.get().setDob(staffDTO.getDob());
            updateById.get().setAddress(staffDTO.getAddress());
            updateById.get().setContact_no(staffDTO.getContact_no());
            updateById.get().setDesignation(staffDTO.getDesignation());
            updateById.get().setName(staffDTO.getName());
            updateById.get().setGender(staffDTO.getGender());
            updateById.get().setJoined_date(staffDTO.getJoined_date());
        }
    }
    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> selectedStaffId = staffDAO.findById(id);
        if(selectedStaffId.isPresent()){
            staffDAO.deleteById(id);
        }else {
            throw new StaffNotFoundException("Staff not found");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return  mapping.convertToStaffDTO(staffDAO.findAll());

    }

    @Override
    public StaffResponse getSelectStaffById(String id) {
        if(staffDAO.existsById(id)){
            Optional<StaffEntity> staffEntityById = staffDAO.findById(id);
            return mapping.convertToStaffDTO(staffEntityById.get());
        }else {
            return new StaffErrorResponse(0, "Staff not found");
        }    }
}
