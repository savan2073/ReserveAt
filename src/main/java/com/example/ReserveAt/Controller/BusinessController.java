package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.BusinessDTO;
import com.example.ReserveAt.Dto.EmployeeDTO;
import com.example.ReserveAt.Dto.LoginDTO;
import com.example.ReserveAt.Dto.WorkingHoursDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.City;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.BusinessService;
import com.example.ReserveAt.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/business")
public class BusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private BusinessService businessService;
    @Autowired
    private EmployeeService employeeService;

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

        logger.info("Business register called");
        String photoPath = saveFileOnServer(photo);
        BusinessDTO businessDTO = new BusinessDTO(null, companyName, city, address, 0.0, description, null, businessType, email, password, null, photoPath, 0, null);

        String id = businessService.addBiz(businessDTO);
        logger.info("Business registered successfully" + businessDTO);
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

    @GetMapping("/card/{businessId}")
    public ResponseEntity<BusinessDTO> getBusinessDetailsCard(@PathVariable Long businessId) {
        BusinessDTO businessDTO = businessService.getBizDetailsCard(businessId);
        return ResponseEntity.ok(businessDTO);
    }

    @GetMapping("/details")
    public ResponseEntity<BusinessDTO> getBusinessDetails(Principal principal) {
        //użycie principal do pobrania email z tokena JWT
        String email = principal.getName();

        //pobieranie szczegółów biznesu na podstawie emaila
        BusinessDTO businessDTO = businessService.getBusinessDetails(email);
        return ResponseEntity.ok(businessDTO);
    }

    @GetMapping("/{businessId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByBusinessId(@PathVariable Long businessId) {
        List<Employee> employees = employeeService.getAllEmployeesByBusinessId(businessId);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeName(employee.getEmployeeName());
        employeeDTO.setEmployeeSurname(employee.getEmployeeSurname());
        return employeeDTO;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BusinessDTO>> searchBusinesses(
            @RequestParam("city") City city,
            @RequestParam("type") BusinessType type,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<BusinessDTO> businessDTOs = businessService.findBusinessesByTypeAndCity(city, type, pageable);
        return ResponseEntity.ok(businessDTOs);
    }


    @GetMapping("/{city}/{businessName}")
    public ResponseEntity<BusinessDTO> getBusinessDetails(@PathVariable String businessName, @PathVariable String city) {
        BusinessDTO businessDTO = businessService.getBusinessByNameAndCity(businessName, city);
        return ResponseEntity.ok(businessDTO);
    }

    //workinghours
    @PostMapping("/{businessId}/working-hours")
    public ResponseEntity<?> addWorkingHours(@PathVariable Long businessId, @RequestBody List<WorkingHoursDTO> workingHoursDTOs) {
        System.out.println("Otrzymane dane godzin pracy: " + workingHoursDTOs);
        businessService.addWorkingHours(businessId, workingHoursDTOs);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{businessId}/working-hours")
    public ResponseEntity<List<WorkingHoursDTO>> getWorkingHours(@PathVariable Long businessId) {
        List<WorkingHoursDTO> workingHours = businessService.getWorkingHours(businessId);
        return ResponseEntity.ok(workingHours);
    }

    @PutMapping("/{businessId}/working-hours")
    public ResponseEntity<?> updateWorkingHours(@PathVariable Long businessId, @RequestBody List<WorkingHoursDTO> workingHoursDTOs) {
        businessService.updateWorkingHours(businessId, workingHoursDTOs);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/working-hours/{workingHoursId}")
    public ResponseEntity<?> deleteWorkingHours(@PathVariable Long workingHoursId) {
        businessService.deleteWorkingHours(workingHoursId);
        return ResponseEntity.ok().build();
    }


}
