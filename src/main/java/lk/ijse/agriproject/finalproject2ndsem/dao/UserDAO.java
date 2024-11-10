package lk.ijse.agriproject.finalproject2ndsem.dao;

import lk.ijse.agriproject.finalproject2ndsem.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDAO extends JpaRepository<UserEntity, String> {

}
