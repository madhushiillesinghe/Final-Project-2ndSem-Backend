package lk.ijse.agriproject.finalproject2ndsem.dao;

import lk.ijse.agriproject.finalproject2ndsem.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDAO extends JpaRepository<FieldEntity, String> {

}
