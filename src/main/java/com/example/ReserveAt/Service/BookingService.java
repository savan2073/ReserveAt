package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.BookingDTO;
import com.example.ReserveAt.Model.Booking;

import java.awt.print.Book;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {

    //sprawdz czy employee jest dostepny
    boolean isEmployeeAvailable(Long employeeId, LocalDate bookingDate, LocalTime startTime, Duration duration);

    Booking createBooking(Long userId, Long employeeId, Long activityId, LocalDate bookingDate, LocalTime startTime, Duration duration);

    List<BookingDTO> getBookingsForEmployee(Long employeeId);

    List<BookingDTO> getBookingsForUser(Long userId);
}
