package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.UserDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private final PasswordEncoder passwordEncoder;

    static Logger logger = LoggerFactory.getLogger(UserController.class);

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void > createUser(@RequestBody UserDTO buildUserDTO ){
//        try {
//            userService.saveUser(buildUserDTO);
//            logger.info("User saved : " + buildUserDTO);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch (DataPersistFailedException e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
@PreAuthorize("hasRole('SCIENTIST')")
@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<UserDTO>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
}

    @PutMapping(value = "/{userEmail}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  updateUser(@PathVariable ("userEmail") String userEmail,@RequestBody UserDTO updateUserdto){
        try {
            String password=updateUserdto.getPassword();
            String encode = passwordEncoder.encode(password);
            updateUserdto.setPassword(encode);
            userService.updateUser(userEmail,updateUserdto);
            logger.info("User updated : " + updateUserdto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{userEmail}")
    public ResponseEntity<String> deleteUser(@PathVariable ("userEmail") String userEmail) {
        try {
            userService.deleteUser(userEmail);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userEmail}",produces =MediaType.APPLICATION_JSON_VALUE )
    public UserResponse getUser(@PathVariable("userEmail") String userEmail){
        return userService.getSelectUserByEmail(userEmail);
    }
}
