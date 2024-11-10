package lk.ijse.agriproject.finalproject2ndsem.dao;

import lk.ijse.agriproject.finalproject2ndsem.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDAO extends JpaRepository<EquipmentEntity, String> {

}
