package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID")
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "activityID", nullable = false)
    private Activity activity;
    @Column(name = "bookingDate", nullable = false)
    private LocalDate bookingDate;
    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;
    @Column(name = "endTime", nullable = false)
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "businessId", nullable = false)
    private Business business;
    @Column(name = "price", nullable = false)
    private double price;

}
