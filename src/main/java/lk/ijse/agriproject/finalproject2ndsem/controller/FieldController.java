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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/fields")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    static Logger logger = LoggerFactory.getLogger(FieldController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity< String> createField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2) {
        try {
            Point location = parsePoint(fieldLocation);

            String base64image1 = AppUtill.toBase64Profilepic(fieldImage1);
            String base64image2 = AppUtill.toBase64Profilepic(fieldImage2);

            FieldDTO buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(fieldCode);
            buildFieldDTO.setFieldLocation(location);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setExtentSize(Double.parseDouble(extentSize));
            buildFieldDTO.setFieldImage1(base64image1);
            buildFieldDTO.setFieldImage2(base64image2);

            fieldService.saveField(buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED); // Return 201 status on success
        } catch (NumberFormatException e) {
            logger.error("Invalid format for field_location: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 on parsing error
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 on data persistence failure
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on general error
        }
    }

    private Point parsePoint(String field_location) {
        try {
            String[] coordinates = field_location.split(",");
            double latitude = Double.parseDouble(coordinates[0].trim());
            double longitude = Double.parseDouble(coordinates[1].trim());
            return new Point((int) latitude, (int) longitude);
        }   catch (Exception e) {
            throw new NumberFormatException("Invalid format for field_location. Expected 'latitude,longitude'.");
        }
    }



        @PutMapping(value = "/{fieldcode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> UpdateField(
            @PathVariable("fieldcode") String fieldcode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2
    ) {

        try {
            String base64ProfilePic = AppUtill.toBase64Profilepic(fieldImage1);
            String base64image2 = AppUtill.toBase64Profilepic(fieldImage2);

            Point location = parsePoint(fieldLocation);

            // build the user object
            FieldDTO buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(fieldcode);
            buildFieldDTO.setFieldLocation(location);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setExtentSize(Double.parseDouble(extentSize));
            buildFieldDTO.setFieldImage1(base64ProfilePic);
            buildFieldDTO.setFieldImage2(base64image2);

            fieldService.updateField(fieldcode,buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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
