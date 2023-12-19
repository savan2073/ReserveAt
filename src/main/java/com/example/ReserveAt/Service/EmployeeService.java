package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.EmployeeDTO;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {



    //create a new employee
    Employee createEmployee(EmployeeDTO employeeDTO);

    //get all employees
    public List<Employee> getAllEmployees();

    public List<Employee> getAllEmployeesByBusinessId(Long businessId);

    //get employee by id
    public Employee getByID(Long id);

    //update existing employee
    public Employee update(Long id, Employee employeeDetails);

    //delete employee
    public void delete(Long id);

}
