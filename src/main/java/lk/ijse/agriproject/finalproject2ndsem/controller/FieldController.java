package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.FieldResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.FieldDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.FieldNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.FieldService;
import lk.ijse.agriproject.finalproject2ndsem.util.AppUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.Point;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fields")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    static Logger logger = LoggerFactory.getLogger(FieldController.class);


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createField(
            @RequestPart("field_code") String field_code,
            @RequestPart("field_name") String field_name,
//            @RequestPart("latitude") double latitude,
//            @RequestPart("longitude") double longitude,
            @RequestPart("extent_size") double extent_size,
            @RequestPart("field_image_1") MultipartFile field_image_1,
            @RequestPart("field_image_2") MultipartFile field_image_2) {

        try {

//            Point field_location = new Point((int) longitude, (int) latitude); // Truncate to int
            // Convert images to Base64 strings
            String base64ProfilePic = AppUtill.toBase64Profilepic(field_image_1);
            String base64image2 = AppUtill.toBase64Profilepic(field_image_2);

            // Log received data
            logger.info("Received field_code: {}", field_code);
            logger.info("Received field_name: {}", field_name);
//            logger.info("Received latitude: {}", latitude);
//            logger.info("Received longitude: {}", longitude);
            logger.info("Received extent_size: {}", extent_size);

            // Build the FieldDTO object
            FieldDTO buildFieldDTO = new FieldDTO();
            buildFieldDTO.setField_code(field_code);
           // buildFieldDTO.setField_location(field_location);  // Here location should be org.locationtech.jts.geom.Point
            buildFieldDTO.setField_name(field_name);
            buildFieldDTO.setExtent_size(extent_size);
            buildFieldDTO.setField_image_1(base64ProfilePic);
            buildFieldDTO.setField_image_2(base64image2);

            // Save field data
            fieldService.saveField(buildFieldDTO);

            return new ResponseEntity<>(HttpStatus.CREATED); // Return 201 status on success
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 on data persistence failure
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on general error
        }
    }


//    @PutMapping(value = "/{fieldcode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Void > UpdateField(
//            @PathVariable("fieldcode") String fieldcode,
//            @RequestPart("fieldName") String fieldName,
//            @RequestPart("latitude") double latitude,
//            @RequestPart("longitude") double longitude,
//            @RequestPart("extentSize") double extentSize,
//            @RequestPart("fieldImage1") MultipartFile fieldImage1,
//            @RequestPart("fieldImage2") MultipartFile fieldImage2
//    ) {
//
//        try {
//            String base64ProfilePic = AppUtill.toBase64Profilepic(fieldImage1);
//            String base64image2 = AppUtill.toBase64Profilepic(fieldImage2);
//
//            // build the user object
//            FieldDTO buildFieldDTO = new FieldDTO();
//            buildFieldDTO.setField_code(fieldcode);
//            buildFieldDTO.setField_location(field_location);
//            buildFieldDTO.setField_name(fieldName);
//            buildFieldDTO.setExtent_size(extentSize);
//            buildFieldDTO.setField_image_1(base64ProfilePic);
//            buildFieldDTO.setField_image_2(base64image2);
//
//            fieldService.updateField(fieldcode,buildFieldDTO);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (FieldNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<String> deleteField(@PathVariable ("code") String code) {
        try {
            fieldService.deleteField(code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/allfields",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllField(){
        return fieldService.getAllFields();
    }
    @GetMapping(value = "/{code}",produces =MediaType.APPLICATION_JSON_VALUE )
    public FieldResponse getField(@PathVariable("code") String code){
        return fieldService.getSelectFieldByCode(code);
    }
}
