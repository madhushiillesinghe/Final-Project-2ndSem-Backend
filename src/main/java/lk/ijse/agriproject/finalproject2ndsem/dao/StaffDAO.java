package lk.ijse.agriproject.finalproject2ndsem.dao;

import lk.ijse.agriproject.finalproject2ndsem.customObj.StaffResponse;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffDAO extends JpaRepository<StaffEntity, String> {
    boolean existsByEmail(String email);
    Optional<StaffEntity> findByEmail(String id);

}
