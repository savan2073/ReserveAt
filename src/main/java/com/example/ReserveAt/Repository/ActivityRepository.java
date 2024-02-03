package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Activity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByEmployeeEmployeeId(Long employeeId);
}
