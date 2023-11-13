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
    @Column(name = "activityID", nullable = false)
    private int id;

    @Column(name = "activityName", nullable = false, length = 30)
    private String activityName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "durationOfTreatment")
    private Duration durationOfTreatment;

    @ManyToOne
    @JoinColumn(name = "employeeID", nullable = false)
    private Employee employee;

    public Activity(String activityName, String description, double price, Duration durationOfTreatment) {
        this.activityName = activityName;
        this.description = description;
        this.price = price;
        this.durationOfTreatment = durationOfTreatment;
    }

}
