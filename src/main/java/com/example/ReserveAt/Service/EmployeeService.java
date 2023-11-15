package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    //get all employees
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    //get employee by id
    public Employee findByID(int id){
        return employeeRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
    //delete employee
    public void delete(int id){
        Employee employee = employeeRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
