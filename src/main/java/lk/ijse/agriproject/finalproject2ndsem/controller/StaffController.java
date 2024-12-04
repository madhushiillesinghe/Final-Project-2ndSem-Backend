package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.StaffResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.StaffDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.StaffNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.UserNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    static Logger logger = LoggerFactory.getLogger(StaffController.class);
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void > createStaff(@RequestBody StaffDTO buildStaffDTO ){
        try {
            staffService.saveStaff(buildStaffDTO);
            logger.info("Staff saved : " + buildStaffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMINISTRATIVE')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  updateStaff(@PathVariable ("id") String id,@RequestBody StaffDTO updateStaffdto){
        try {
            staffService.updateStaff(id,updateStaffdto);
            logger.info("Staff updated : " + updateStaffdto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMINISTRATIVE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable ("id") String id) {
        try {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/allstaff",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff(){
        return staffService.getAllStaff();
    }
    @GetMapping(value = "/{id}",produces =MediaType.APPLICATION_JSON_VALUE )
    public StaffResponse getStaff(@PathVariable("id") String id){
        return staffService.getSelectStaffById(id);
    }

}
