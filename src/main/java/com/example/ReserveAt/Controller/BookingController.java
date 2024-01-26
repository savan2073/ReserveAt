package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.BookingDto;
import com.example.ReserveAt.Model.Booking;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Response.UnavailableEmployeeException;
import com.example.ReserveAt.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto, Principal principal) {
        try {
            String userEmail = principal.getName();
            Optional<User> userOptional = userRepository.findByEmail(userEmail);

            if (!userOptional.isPresent()) {
                // Jeśli użytkownik nie istnieje, zwróć status UNAUTHORIZED
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            User user = userOptional.get();
            Booking newBooking = bookingService.createBooking(
                    bookingDto.getUserId(),
                    bookingDto.getEmployeeId(),
                    bookingDto.getActivityId(),
                    bookingDto.getBookingDate(),
                    bookingDto.getStartTime(),
                    bookingDto.getDuration()
            );
            return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
        } catch (UnavailableEmployeeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(UnavailableEmployeeException.class)
    public ResponseEntity<Object> handleUnavailableEmployee(UnavailableEmployeeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
