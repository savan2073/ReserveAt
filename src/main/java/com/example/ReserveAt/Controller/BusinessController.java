package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.CompanyDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/register")
    public String saveBusiness(@RequestBody CompanyDTO companyDTO) {
        String id = businessService.addBiz(companyDTO);
        System.out.println(companyDTO);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginBiz(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginMessage = businessService.loginBiz(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }
}
