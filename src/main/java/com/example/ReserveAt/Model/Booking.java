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
    private int bookingId;
    @Column(name = "userID")
    private int userId;
    @Column(name = "activityID")
    private int activityId;
    @Column(name = "providerID")
    private int providerId;
    @Column(name = "bookingDate")
    private Date bookingDate;
    //booking time
    //booking status
}
