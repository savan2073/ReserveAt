package com.example.ReserveAt.Controller;


import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }
    //get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getByID(id);
        return ResponseEntity.ok(employee);
    }

    //update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.update(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    //delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }


}
