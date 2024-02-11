package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.BookingDTO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingImplementation implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingImplementation.class);
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
        logger.info("Checking availability for EmployeeId: {}", employeeId);
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
        logger.info("UserId: {}, EmployeeId: {}, ActivityId: {}", userId, employeeId, activityId);
        if (isEmployeeAvailable(employeeId, bookingDate, startTime, duration)) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
            Activity activity = activityRepository.findById(activityId)
                    .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));
            long durationMinutes = duration.getSeconds();
            logger.info("Duration przekazywane: " + duration + ", i duration po konwersji: " + durationMinutes);
            //tworzenie nowej rezerwacji
            Booking booking = new Booking();
            //szczegoly rezerwacji
            booking.setUser(user);
            booking.setEmployee(employee);
            booking.setActivity(activity);
            booking.setBookingDate(bookingDate);
            booking.setStartTime(startTime);
            booking.setEndTime(startTime.plusMinutes(durationMinutes));
            booking.setPrice(activity.getPrice());
            booking.setBusiness(employee.getBusiness());
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new UnavailableEmployeeException("Pracownik nie jest dostępny w wybranym terminie.");
        }
    }

    @Override
    public List<BookingDTO> getBookingsForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        return convertToDTOList(bookingRepository.findAllByEmployeeEmployeeId(employeeId));
    }

    private List<BookingDTO> convertToDTOList(List<Booking> bookings) {
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getUser().getUserId(),
                booking.getEmployee().getEmployeeId(),
                booking.getActivity().getId(),
                booking.getActivity().getActivityName(),
                booking.getBookingDate(),
                booking.getStartTime(),
                booking.getActivity().getDurationOfTreatment()
        );
    }
}
