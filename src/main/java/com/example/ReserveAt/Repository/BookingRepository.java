package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Booking;
import com.example.ReserveAt.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    //znajdz wszystkie rezerwacje, ktore nachodza na podany czas dla danego pracownika
    List<Booking> findByEmployeeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Employee employee, LocalTime startTime, LocalTime endTime);
}
