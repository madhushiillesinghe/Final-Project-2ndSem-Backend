package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.VehicleResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.VehicleErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.StaffDAO;
import lk.ijse.agriproject.finalproject2ndsem.dao.VehicleDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.VehicleDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.VehicalEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.CropNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.VehicleNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.VehicleService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        VehicalEntity vehicalEntity = mapping.convertToVehicleEntity(vehicleDTO);
        VehicalEntity vehiclesave= vehicleDAO.save(vehicalEntity);
        System.out.println(vehicalEntity+"At impl");
         if(vehiclesave == null && vehiclesave.getVehicle_code()== null ) {
            throw new DataPersistFailedException("Cannot saved Vehicle");
        }
    }

    @Override
    public void updateVehicle(String code,VehicleDTO vehicleDTO) {
        Optional<VehicalEntity> updateByCode=vehicleDAO.findById(code);
        Optional<StaffEntity> staffDAOById = staffDAO.findById(vehicleDTO.getStaff_id());
        if(!updateByCode.isPresent()){
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
            updateByCode.get().setVehicle_category(vehicleDTO.getVehicle_category());
            updateByCode.get().setStatus(vehicleDTO.getStatus());
            updateByCode.get().setRemarks(vehicleDTO.getRemarks());
            updateByCode.get().setFuel_type(vehicleDTO.getFuel_type());
            updateByCode.get().setLicense_plate_no(vehicleDTO.getLicense_plate_no());
            updateByCode.get().setStaff(staffDAOById.get());
        }
    }

    @Override
    public void deleteVehicle(String code) {
        Optional<VehicalEntity> selectedVehicleCode = vehicleDAO.findById(code);
        if(selectedVehicleCode.isPresent()){
            vehicleDAO.deleteById(code);
        }else {
            throw new VehicleNotFoundException("Vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return  mapping.convertToVehicleDTO(vehicleDAO.findAll());
    }
    @Override
    public VehicleResponse getSelectVehicleByCode(String code) {
        if(vehicleDAO.existsById(code)){
            Optional<VehicalEntity> vehicleEntityByCode = vehicleDAO.findById(code);
            return mapping.convertToVehicleDTO(vehicleEntityByCode.get());
        }else {
            return new VehicleErrorResponse(0, "vehicle not found");
        }
    }
}
