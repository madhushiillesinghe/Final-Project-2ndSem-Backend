package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.dao.MoniteringLogDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.StaffDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.MoniteringLogEntity;
import lk.ijse.agriproject.finalproject2ndsem.service.LogStaffService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogStaffServiceIMPL implements LogStaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private MoniteringLogDAO moniteringLogDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void updateMoniteringLogStaff(String id, List<StaffDTO> staffDTOS) {
        Optional<MoniteringLogEntity> updateById=moniteringLogDAO.findById(id);

    }
}
