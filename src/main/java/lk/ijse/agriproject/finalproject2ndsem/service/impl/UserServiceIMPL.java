package lk.ijse.agriproject.finalproject2ndsem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.impl.UserErrorResponse;
import lk.ijse.agriproject.finalproject2ndsem.dao.UserDAO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.UserEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.UserService;
import lk.ijse.agriproject.finalproject2ndsem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity usersave= userDao.save(mapping.convertToUserEntity(userDTO));
        if(usersave == null && usersave.getEmail() == null ) {
            throw new DataPersistFailedException("Cannot saved user");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> updateByEmail=userDao.findById(userDTO.getEmail());
        if(!updateByEmail.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            updateByEmail.get().setEmail(userDTO.getEmail());
            updateByEmail.get().setPassword(userDTO.getPassword());
            updateByEmail.get().setRole(userDTO.getRole());
        }
    }

    @Override
    public void deleteUser(String id) {
        Optional<UserEntity> selectedUserEmail = userDao.findById(id);
        if(selectedUserEmail.isPresent()){
            userDao.deleteById(id);
        }else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return  mapping.convertToUserDTO(userDao.findAll());
    }
    @Override
    public UserResponse getSelectUserByEmail(String userEmail) {
        if(userDao.existsById(userEmail)){
            Optional<UserEntity> userEntityByUserEmail = userDao.findById(userEmail);
            return mapping.convertToUserDTO(userEntityByUserEmail.get());
        }else {
            return new UserErrorResponse(0, "User not found");
        }
    }
}
