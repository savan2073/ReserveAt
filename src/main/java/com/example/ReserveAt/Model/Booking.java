package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "userID")
    private Long userId;
    @Column(name = "activityID")
    private Long activityId;
    @Column(name = "providerID")
    private Long providerId;
    @Column(name = "bookingDate")
    private Date bookingDate;
    //booking time
    //booking status
}
