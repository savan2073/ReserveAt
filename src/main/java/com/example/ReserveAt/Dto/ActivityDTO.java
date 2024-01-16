package com.example.ReserveAt.Dto;

import java.time.Duration;

public class ActivityDTO {
    private Long id;
    private String activityName;
    private String description;
    private double price;
    private int durationOfTreatment; // Czas trwania w minutach

    public ActivityDTO() {
    }

    public ActivityDTO(String activityName, String description, double price, int durationOfTreatment) {
        this.activityName = activityName;
        this.description = description;
        this.price = price;
        this.durationOfTreatment = durationOfTreatment;
    }

    public ActivityDTO(Long id, String activityName, String description, double price, int durationOfTreatment) {
        this.id = id;
        this.activityName = activityName;
        this.description = description;
        this.price = price;
        this.durationOfTreatment = durationOfTreatment;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDurationOfTreatment() {
        return durationOfTreatment;
    }

    public void setDurationOfTreatment(int durationOfTreatment) {
        this.durationOfTreatment = durationOfTreatment;
    }
}
