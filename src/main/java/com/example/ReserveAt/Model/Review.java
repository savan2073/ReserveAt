package com.example.ReserveAt.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    @Column(name = "rating")
    private double rating;
    @Column(name = "contentOfReview", nullable = false, length = 500)
    private String content;
    @Column(name = "reviewDate")
    private LocalDate reviewDate;
    @ManyToOne
    @JoinColumn(name = "businessId")
    @JsonIgnore
    private Business business;

    public void setRating(double rating) {
        if (rating >= 1 && rating <= 5 && ((rating * 2) % 1 == 0)) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5 with steps of 0.5");
        }
    }
}
