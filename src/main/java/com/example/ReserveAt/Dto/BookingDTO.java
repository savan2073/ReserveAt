package com.example.ReserveAt.Dto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDTO {
    private Long userId;
    private Long employeeId;
    private Long activityId;
    private String activityName;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration duration;
    private Long businessId;
    private String businessName;


    public BookingDTO(Long userId, Long employeeId, Long activityId, String activityName, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, Duration duration, Long businessId, String businessName) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.activityId = activityId;
        this.activityName = activityName;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.businessId = businessId;
        this.businessName = businessName;
    }

    public BookingDTO() {
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
