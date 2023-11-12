package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Table(name = "activity")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "activityName")
    private String activityName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    private Duration durationOfTreatment;

    public Activity(String activityName, String description, double price, Duration durationOfTreatment) {
        this.activityName = activityName;
        this.description = description;
        this.price = price;
        this.durationOfTreatment = durationOfTreatment;
    }

}
