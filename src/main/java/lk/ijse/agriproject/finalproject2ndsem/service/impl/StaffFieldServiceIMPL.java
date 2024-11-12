package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.dao.StaffDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.service.StaffFieldService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StaffFieldServiceIMPL implements StaffFieldService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaffField(StaffDTO staffDTO) {
        StaffEntity staffsave= staffDAO.save(mapping.convertToStaffEntity(staffDTO));
        if(staffsave == null && staffsave.getId() == null ) {
            throw new DataPersistFailedException("Cannot saved Staff Member");
        }

    }
}
