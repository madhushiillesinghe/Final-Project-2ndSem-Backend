package lk.ijse.agriproject.finalproject2ndsem.service;

import lk.ijse.agriproject.finalproject2ndsem.customObj.VehicleResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(String code,VehicleDTO vehicleDTO);
    void deleteVehicle(String code);
    List<VehicleDTO> getAllVehicles();
    VehicleResponse getSelectVehicleByCode(String code);

}
