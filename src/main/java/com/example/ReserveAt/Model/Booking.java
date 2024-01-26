package com.example.ReserveAt.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "activityID", nullable = false)
    @JsonIgnore
    private Activity activity;
    @Column(name = "bookingDate", nullable = false)
    private LocalDate bookingDate;
    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;
    @Column(name = "endTime", nullable = false)
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "businessId", nullable = false)
    @JsonIgnore
    private Business business;
    @Column(name = "price", nullable = false)
    private double price;

}
