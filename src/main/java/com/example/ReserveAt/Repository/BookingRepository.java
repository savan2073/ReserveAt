package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Booking;
import com.example.ReserveAt.Model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    //znajdz wszystkie rezerwacje, ktore nachodza na podany czas dla danego pracownika
    List<Booking> findByEmployeeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Employee employee, LocalTime startTime, LocalTime endTime);

    List<Booking> findAllByEmployeeEmployeeId(Long employeeId);
    //usuwanie rezerwacji powiązanych z podanym ID aktywności
    @Modifying
    @Transactional
    @Query("DELETE FROM Booking b WHERE b.activity.id = :activityId")
    void deleteAllByActivityId(@Param("activityId") Long activityId);
}
