package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.BookingDTO;
import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Model.Booking;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Response.UnavailableEmployeeException;
import com.example.ReserveAt.Service.ActivityService;
import com.example.ReserveAt.Service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDto, Principal principal) {
        logger.info("Received booking data: {}", bookingDto);
        try {
            String userEmail = principal.getName();
            Optional<User> userOptional = userRepository.findByEmail(userEmail);

            if (!userOptional.isPresent()) {
                // Jeśli użytkownik nie istnieje, zwróć status UNAUTHORIZED
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            User user = userOptional.get();
            logger.info("UserId from token: {}", user.getUserId());
            Booking newBooking = bookingService.createBooking(
                    bookingDto.getUserId(),
                    bookingDto.getEmployeeId(),
                    bookingDto.getActivityId(),
                    bookingDto.getBookingDate(),
                    bookingDto.getStartTime(),
                    bookingDto.getDuration()
            );
            //wysyłanie maila
            long durationMinutes = bookingDto.getDuration().toSeconds();
            Activity activity = activityService.findById(bookingDto.getActivityId());
            //dane do linku
            String serviceName = activity.getActivityName(); // Nazwa usługi
            LocalDate bookingDate = bookingDto.getBookingDate(); // Data rezerwacji
            LocalTime startTime = bookingDto.getStartTime(); //czas startu usług
            String businessName = bookingDto.getBusinessName(); // Nazwa salonu
            String startDateTime = bookingDate.atTime(startTime).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
            String endDateTime = bookingDate.atTime(startTime.plusMinutes(durationMinutes)).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
            String eventTitle = String.format("Rezerwacja: %s", serviceName);
            String details = String.format("Rezerwacja usługi '%s' w salonie %s. Cena: %.2f zł.", serviceName, businessName, activity.getPrice());
            String googleCalendarUrl = String.format("https://www.google.com/calendar/render?action=TEMPLATE&text=%s&dates=%s/%s&details=%s",
                    URLEncoder.encode(eventTitle, StandardCharsets.UTF_8.toString()),
                    startDateTime,
                    endDateTime,
                    URLEncoder.encode(details, StandardCharsets.UTF_8.toString())
            );
            //mail
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("reserveat@wp.pl");
            message.setTo(userEmail);
            message.setSubject("Potwierdzenie rezerwacji.");
            String mailContent = String.format("Drogi(a) %s,\n\n" +
                            "Twoja rezerwacja została utworzona pomyślnie. Oto szczegóły Twojej rezerwacji:\n\n" +
                            "Data rezerwacji: %s\n" +
                            "Godzina rozpoczęcia: %s\n" +
                            "Czas trwania: %d minut\n" +
                            "Usługa: %s\n" +
                            "Cena: %.2f zł\n\n" +
                            "Jeśli masz jakiekolwiek pytania, skontaktuj się z nami.\n\n" +
                            "Utwórz wydarzenie w swoim kalendarzu google: %s\n\n" +
                            "Z pozdrowieniami,\n" +
                            "Twój Salon",
                            user.getFirstName(),
                    bookingDto.getBookingDate().toString(),
                    bookingDto.getStartTime().toString(),
                    durationMinutes,
                    activity.getActivityName(),
                    activity.getPrice(),
                    googleCalendarUrl
                    );
            message.setText(mailContent);
            mailSender.send(message);
            return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
        } catch (UnavailableEmployeeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(UnavailableEmployeeException.class)
    public ResponseEntity<Object> handleUnavailableEmployee(UnavailableEmployeeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<BookingDTO>> getBookingsForEmployee(@PathVariable Long employeeId) {
        List<BookingDTO> bookings = bookingService.getBookingsForEmployee(employeeId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsForUser(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getBookingsForUser(userId);
        return ResponseEntity.ok(bookings);
    }
}
