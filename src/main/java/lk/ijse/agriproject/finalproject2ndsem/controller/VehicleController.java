package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.VehicleResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.VehicleDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.VehicleNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.VehicleService;
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
@RequestMapping(value = "/api/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    static Logger logger = LoggerFactory.getLogger(VehicleController.class);
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void > createVehicle(@RequestBody VehicleDTO buildVehicleDTO ){
        try {
            vehicleService.saveVehicle(buildVehicleDTO);
            logger.info("Vehicle saved : " + buildVehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMINISTRATIVE')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  updateVehicle(@PathVariable ("id") String id,@RequestBody VehicleDTO updateVehicledto){
        try {
            vehicleService.updateVehicle(id,updateVehicledto);
            logger.info("Vehicle updated : " + updateVehicledto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMINISTRATIVE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable ("id") String id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/allvehicles",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicle(){
        return vehicleService.getAllVehicles();
    }
    @GetMapping(value = "/{id}",produces =MediaType.APPLICATION_JSON_VALUE )
    public VehicleResponse getVehicle(@PathVariable("id") String id){
        return vehicleService.getSelectVehicleByCode(id);
    }

}
