package com.example.ReserveAt.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Booking {
    private int bookingId;
    private int userId;
    private int activityId;
    private int providerId;
    private Date bookingDate;
    //booking time
    //booking status
}
