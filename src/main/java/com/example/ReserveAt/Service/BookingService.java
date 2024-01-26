package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.Booking;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingService {

    //sprawdz czy employee jest dostepny
    boolean isEmployeeAvailable(Long employeeId, LocalDate bookingDate, LocalTime startTime, Duration duration);

    Booking createBooking(Long userId, Long employeeId, Long activityId, LocalDate bookingDate, LocalTime startTime, Duration duration);
}
