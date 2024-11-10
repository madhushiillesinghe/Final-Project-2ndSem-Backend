package lk.ijse.agriproject.finalproject2ndsem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthTestController {
    @GetMapping
    public String healthCheck(){
        return "Agri project is running";
    }
}
