package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByBusinessBusinessId(Long businessId);
}
