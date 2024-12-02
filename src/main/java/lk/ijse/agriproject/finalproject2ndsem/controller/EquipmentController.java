package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.EquipmentResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.EquipmentDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.EquipmentNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    static Logger logger = LoggerFactory.getLogger(EquipmentController.class);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void > createEquipment(@RequestBody EquipmentDTO buildEquipmentDTO ){
        try {
            equipmentService.saveEquipment(buildEquipmentDTO);
            logger.info("Equipment saved : " + buildEquipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{code}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>  updateEquipment(@PathVariable ("code") String code,@RequestBody EquipmentDTO updateEquipmentdto){
        try {
            equipmentService.updateEquipment(code,updateEquipmentdto);
            logger.info("Equipment updated : " + updateEquipmentdto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<String> deleteEquipment(@PathVariable ("code") String code) {
        try {
            equipmentService.deleteEquipment(code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/allequipment",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipment(){
        return equipmentService.getAllEquipments();
    }
    @GetMapping(value = "/{code}",produces =MediaType.APPLICATION_JSON_VALUE )
    public EquipmentResponse getVehicle(@PathVariable("code") String code){
        return equipmentService.getSelectEquipmentByCode(code);
    }
}
