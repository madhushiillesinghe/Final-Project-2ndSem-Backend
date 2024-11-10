package lk.ijse.agriproject.finalproject2ndsem.util;

import lk.ijse.agriproject.finalproject2ndsem.dto.impl.*;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

//    staff mapping
    public StaffDTO convertToStaffDTO(StaffEntity staff){
        return modelMapper.map(staff, StaffDTO.class);
    }
    public StaffEntity convertToStaffEntity(StaffDTO dto) {
        return modelMapper.map(dto, StaffEntity.class);
    }
    public List<StaffDTO> convertToStaffDTO(List<StaffEntity> staffEntities){
        return modelMapper.map(staffEntities,new TypeToken<List<StaffDTO>>() {}.getType());
    }


    //user mapping
    public UserDTO convertToUserDTO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public UserEntity convertToUserEntity(UserDTO userDTO){

        return modelMapper.map(userDTO, UserEntity.class);
    }
    public List<UserDTO> convertToUserDTO(List<UserEntity> users ){
        return modelMapper.map(users,new TypeToken<List<UserDTO>>() {}.getType());
    }

    //field mapping
    public FieldDTO convertToFieldDTO(FieldEntity fieldEntity){
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }
    public FieldEntity convertToFieldEntity(FieldDTO fieldDTO){
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }
    public List<FieldDTO> convertToFieldDTO(List<FieldEntity> fields ){
        return modelMapper.map(fields,new TypeToken<List<FieldDTO>>() {}.getType());
    }

    //crop mapping
    public CropDTO convertToCropDTO(CropEntity cropEntity){
        return modelMapper.map(cropEntity, CropDTO.class);
    }
    public CropEntity convertToCropEntity(CropDTO cropDTO){
        return modelMapper.map(cropDTO, CropEntity.class);
    }
    public List<CropDTO> convertToCropDTO(List<CropEntity> crops ){
        return modelMapper.map(crops,new TypeToken<List<CropDTO>>() {}.getType());
    }

    //vehicle mapping
    public VehicleDTO convertToVehicleDTO(VehicalEntity vehicleEntity){
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }
    public VehicalEntity convertToVehicleEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, VehicalEntity.class);
    }
    public List<VehicleDTO> convertToVehicleDTO(List<VehicalEntity> vehicles ){
        return modelMapper.map(vehicles,new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    //equipment mapping
    public EquipmentDTO convertToEquipmentDTO(EquipmentEntity equipmentEntity){
        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
    }
    public EquipmentEntity convertToEquipmentEntity(EquipmentDTO equipmentDTO){
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }
    public List<EquipmentDTO> convertToEquipmentDTO(List<EquipmentEntity> equipments ){
        return modelMapper.map(equipments,new TypeToken<List<EquipmentDTO>>() {}.getType());
    }


    //moniteringLog mapping
    public MoniteringLogDTO convertToMoniteringLogDTO(MoniteringLogEntity moniteringLogEntity){
        return modelMapper.map(moniteringLogEntity, MoniteringLogDTO.class);
    }
    public MoniteringLogEntity convertToMoniteringLogEntity(MoniteringLogDTO moniteringLogDTO){
        return modelMapper.map(moniteringLogDTO, MoniteringLogEntity.class);
    }
    public List<MoniteringLogDTO> convertToMoniteringLogDTO(List<MoniteringLogEntity> moniteringLogs ){
        return modelMapper.map(moniteringLogs,new TypeToken<List<MoniteringLogDTO>>() {}.getType());
    }

}
