package com.example.ReserveAt.Dto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDto {
    private Long userId;
    private Long employeeId;
    private Long activityId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private Duration duration;

    public BookingDto(Long userId, Long employeeId, Long activityId, LocalDate bookingDate, LocalTime startTime, Duration duration) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.activityId = activityId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.duration = duration;
    }

    public BookingDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
