package lk.ijse.agriproject.finalproject2ndsem.controller;

import lk.ijse.agriproject.finalproject2ndsem.dto.impl.MoniteringLogDTO;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.CropEntity;
import lk.ijse.agriproject.finalproject2ndsem.entity.impl.StaffEntity;
import lk.ijse.agriproject.finalproject2ndsem.exception.MoniteringLogNotFoundException;
import lk.ijse.agriproject.finalproject2ndsem.service.FieldService;
import lk.ijse.agriproject.finalproject2ndsem.service.MoniteringLogService;
import lk.ijse.agriproject.finalproject2ndsem.service.StaffService;
import lk.ijse.agriproject.finalproject2ndsem.util.AppUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*" ,allowedHeaders ="*" )
@RestController
@RequestMapping(value = "/api/v1/logstaff")
public class MoniteringLogStaffController {
    @Autowired
    private MoniteringLogService moniteringLogService;
    static Logger logger = LoggerFactory.getLogger(MoniteringLogStaffController.class);

    @PutMapping(value = "/{logcode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateMonitoringLog(
            @RequestParam("staff") List<StaffEntity> staff,
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
            moniteringLogDTO.setStaff(staff);

            // Save the monitoring log
            moniteringLogService.updateMoniteringLogStaff(logCode, moniteringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED); // Return 201 status on success

        } catch (MoniteringLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
