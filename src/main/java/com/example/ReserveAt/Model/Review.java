package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long reviewId;
    @Column(name = "userID")
    private Long userId;
    @Column(name = "providerID")
    private Long providerId;
    @Column(name = "rating")
    private double rating;
    @Column(name = "contentOfReview")
    private String content;
    @Column(name = "reviewDate")
    private Date reviewDate;
}
