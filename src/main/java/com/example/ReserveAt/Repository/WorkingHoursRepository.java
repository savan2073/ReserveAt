package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
    List<WorkingHours> findByBusinessBusinessId(Long businessId);
}
