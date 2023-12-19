package com.example.ReserveAt.Controller;


import com.example.ReserveAt.Dto.EmployeeDTO;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(newEmployee);
    }
}
