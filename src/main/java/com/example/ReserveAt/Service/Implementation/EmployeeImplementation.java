package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.EmployeeDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Repository.BusinessRepository;
import com.example.ReserveAt.Repository.EmployeeRepository;
import com.example.ReserveAt.Service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Long businessId = employeeDTO.getBusinessId();
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new EntityNotFoundException("Business not found with id: " + businessId));

        Employee employee = new Employee();
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setEmployeeSurname(employeeDTO.getEmployeeSurname());
        employee.setBusiness(business);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeesByBusinessId(Long businessId) {
        return employeeRepository.findByBusinessBusinessId(businessId);
    }

    @Override
    public Employee getByID(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee update(Long id, Employee employeeDetails) {
        Employee employee = getByID(id);
        // Zaktualizuj pola pracownika
        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeSurname(employeeDetails.getEmployeeSurname());
        // inne pola do zaktualizowania
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
