package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.CropResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.CropDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.CropNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.service.CropService;
import lk.ijse.agriproject.finalproject2ndsem.util.AppUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/crops")
public class CropController {
    @Autowired
    private CropService cropService;
    static Logger logger = LoggerFactory.getLogger(CropController.class);
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void > createCrop(
            @RequestPart("code") String code,
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("fieldCode") String fieldCode
    ) {

        try {
            String base64ProfilePic = AppUtill.toBase64Profilepic(cropImage);
            // build the user object
            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCategory(category);
            buildCropDTO.setCode(code);
            buildCropDTO.setSeason(season);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setFieldCode(fieldCode);
            buildCropDTO.setCropImage(base64ProfilePic);

            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_SCIENTIST')")
    @PutMapping(value = "/{code}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void > UpdateCrop(
            @PathVariable("code") String code,
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("fieldCode") String fieldCode
    ) {

        try {
            String base64ProfilePic = AppUtill.toBase64Profilepic(cropImage);
            // build the user object
            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCategory(category);
            buildCropDTO.setCode(code);
            buildCropDTO.setSeason(season);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setFieldCode(fieldCode);
            buildCropDTO.setCropImage(base64ProfilePic);

            cropService.updateCrop(code,buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_SCIENTIST')")
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<String> deleteCrop(@PathVariable ("code") String code) {
        try {
            cropService.deleteCrop(code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/allcrops",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrop(){
        return cropService.getAllCrops();
    }
    @GetMapping(value = "/{code}",produces =MediaType.APPLICATION_JSON_VALUE )
    public CropResponse getCrop(@PathVariable("code") String code){
        return cropService.getSelectCropByCode(code);
    }
}