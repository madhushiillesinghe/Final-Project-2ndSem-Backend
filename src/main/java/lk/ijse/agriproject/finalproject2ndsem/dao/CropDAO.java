package lk.ijse.agriproject.finalproject2ndsem.dao;

import lk.ijse.agriproject.finalproject2ndsem.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDAO extends JpaRepository<CropEntity, String> {

}
