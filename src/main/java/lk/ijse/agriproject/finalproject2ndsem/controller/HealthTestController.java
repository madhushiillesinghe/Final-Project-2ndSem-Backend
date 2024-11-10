package lk.ijse.agriproject.finalproject2ndsem.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthTestController {
    static Logger logger = LoggerFactory.getLogger(HealthTestController.class);

    @GetMapping
    public String healthCheck(){
        logger.info("Spring boot Agri API is running");
        return "Agri project is running";
    }
}
