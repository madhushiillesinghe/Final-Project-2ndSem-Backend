package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.customObj.FieldResponse;
import lk.ijse.agriproject.finalproject2ndsem.customObj.MoniteringLogResponse;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.FieldDTO;
import lk.ijse.agriproject.finalproject2ndsem.dto.impl.MoniteringLogDTO;
import lk.ijse.agriproject.finalproject2ndsem.exception.DataPersistFailedException;
import lk.ijse.agriproject.finalproject2ndsem.exception.FieldNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.exception.MoniteringLogNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.MoniteringLogService;
import lk.ijse.agriproject.finalproject2ndsem.util.AppUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/logs")
public class MoniteringLogController {
    @Autowired
    private MoniteringLogService moniteringLogService;
    static Logger logger = LoggerFactory.getLogger(MoniteringLogController.class);



// Rest of your imports

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createMonitoringLog(
            @RequestPart("logCode") String logCode,
            @RequestPart("logDate") String logDate,
            @RequestPart("observation") String observation,
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("observedImage") MultipartFile observedImage) {
        try {
            // Convert image to base64
            String base64image1 = AppUtill.toBase64Profilepic(observedImage);

            // Parse logDate String to LocalDate (assuming "yyyy-MM-dd" format)
            LocalDate parsedLogDate;
            Date parsedLogDates=new Date();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                parsedLogDate = LocalDate.parse(logDate, formatter);
            } catch (DateTimeParseException e) {
                logger.error("Invalid date format for logDate: {}", e.getMessage());
                return new ResponseEntity<>("Invalid date format. Expected format: yyyy-MM-dd", HttpStatus.BAD_REQUEST);

            }

            parsedLogDates = java.sql.Date.valueOf(parsedLogDate);
            // Build the MonitoringLogDTO
            MoniteringLogDTO moniteringLogDTO = new MoniteringLogDTO();
            moniteringLogDTO.setLogCode(logCode);
            moniteringLogDTO.setLogDate(parsedLogDates); // assuming logDate in DTO is of type LocalDate
            moniteringLogDTO.setObservation(observation);
            moniteringLogDTO.setFieldCode(fieldCode);
            moniteringLogDTO.setObservedImage(base64image1);

            // Save the monitoring log
            moniteringLogService.saveMoniteringLog(moniteringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED); // Return 201 status on success

        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on general error
        }
    }

    @PutMapping(value = "/{logcode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateMonitoringLog(
            @RequestPart("logCode") String logCode,
            @RequestPart("logDate") String logDate,
            @RequestPart("observation") String observation,
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("observedImage") MultipartFile observedImage) {
        try {
            // Convert image to base64
            String base64image1 = AppUtill.toBase64Profilepic(observedImage);

            // Parse logDate String to LocalDate (assuming "yyyy-MM-dd" format)
            LocalDate parsedLogDate;
            Date parsedLogDates = new Date();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                parsedLogDate = LocalDate.parse(logDate, formatter);
            } catch (DateTimeParseException e) {
                logger.error("Invalid date format for logDate: {}", e.getMessage());
                return new ResponseEntity<>("Invalid date format. Expected format: yyyy-MM-dd", HttpStatus.BAD_REQUEST);

            }

            parsedLogDates = java.sql.Date.valueOf(parsedLogDate);
            // Build the MonitoringLogDTO
            MoniteringLogDTO moniteringLogDTO = new MoniteringLogDTO();
            moniteringLogDTO.setLogCode(logCode);
            moniteringLogDTO.setLogDate(parsedLogDates); // assuming logDate in DTO is of type LocalDate
            moniteringLogDTO.setObservation(observation);
            moniteringLogDTO.setFieldCode(fieldCode);
            moniteringLogDTO.setObservedImage(base64image1);

            // Save the monitoring log
            moniteringLogService.updateMoniteringLog(logCode, moniteringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED); // Return 201 status on success

        } catch (MoniteringLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<String> deleteLog(@PathVariable ("code") String code) {
        try {
            moniteringLogService.deleteMoniteringLog(code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/alllogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MoniteringLogDTO> getAllLog () {
        return moniteringLogService.getAllMoniteringLogs();
    }

    @GetMapping(value = "/{code}",produces =MediaType.APPLICATION_JSON_VALUE )
    public MoniteringLogResponse getLog(@PathVariable("code") String code){
        return moniteringLogService.getSelectMoniteringLogById(code);
        }
    }


