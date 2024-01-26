package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Model.Booking;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.ActivityRepository;
import com.example.ReserveAt.Repository.BookingRepository;
import com.example.ReserveAt.Repository.EmployeeRepository;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Response.UnavailableEmployeeException;
import com.example.ReserveAt.Service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingImplementation implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Override
    public boolean isEmployeeAvailable(Long employeeId, LocalDate bookingDate, LocalTime startTime, Duration duration) {
        LocalTime endTime = startTime.plus(duration);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        //pobieranie wszystkich istniejacych rezerwacji dla danego pracownika w danym dniu
        List<Booking> existingBookings = bookingRepository.findByEmployeeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                employee, startTime, endTime);

        //sprawdzanie czy ktorykolwiek z istniejacych terminow pokrywa sie z żądanym czasem
        for (Booking booking : existingBookings) {
            if (!booking.getBookingDate().equals(bookingDate)){
                continue; //pomijanie rezerwacji z innych dni
            }
            if (startTime.isBefore(booking.getEndTime()) && endTime.isAfter(booking.getStartTime())) {
                return false; //pracownik jest zajety
            }
        }

        return true; //pracownik jest dostepny
    }

    @Override
    public Booking createBooking(Long userId, Long employeeId, Long activityId, LocalDate bookingDate, LocalTime startTime, Duration duration) {
        if (isEmployeeAvailable(employeeId, bookingDate, startTime, duration)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
            Activity activity = activityRepository.findById(activityId)
                    .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));
            //tworzenie nowej rezerwacji
            Booking booking = new Booking();
            //szczegoly rezerwacji
            booking.setUser(user);
            booking.setEmployee(employee);
            booking.setActivity(activity);
            booking.setBookingDate(bookingDate);
            booking.setStartTime(startTime);
            booking.setEndTime(startTime.plus(duration));
            booking.setPrice(activity.getPrice());
            booking.setBusiness(employee.getBusiness());
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new UnavailableEmployeeException("Pracownik nie jest dostępny w wybranym terminie.");
        }
    }
}
