package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.CompanyDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.City;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@CrossOrigin
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/register")
    public ResponseEntity<?> saveBusiness(
            @RequestParam("companyName") String companyName,
            @RequestParam("city") City city,
            @RequestParam("address") String address,
            @RequestParam("description") String description,
            @RequestParam("businessType") BusinessType businessType,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("photo") MultipartFile photo) {

        String photoPath = saveFileOnServer(photo);
        CompanyDTO companyDTO = new CompanyDTO(null, companyName, city, address, 0.0, description, null, businessType, email, password, null, photoPath);

        String id = businessService.addBiz(companyDTO);

        return ResponseEntity.ok(id);
    }

    private String saveFileOnServer(MultipartFile file) {
        String uploadDir = "uploadDir";

        if (file.isEmpty()) {
            return uploadDir + "defaultBiz.jpg";
        }
        try {
            String destinationPath = uploadDir + "/" + file.getOriginalFilename();
            Path path = Paths.get(destinationPath).toAbsolutePath().normalize();
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return destinationPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginBiz(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginMessage = businessService.loginBiz(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }
}
