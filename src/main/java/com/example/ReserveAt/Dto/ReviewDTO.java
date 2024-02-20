package com.example.ReserveAt.Dto;

import java.time.LocalDate;

public class ReviewDTO {
    private Long userId;
    private Long businessId;
    private Double rating;
    private String content;
    private LocalDate reviewDate;
    private String firstName;
    private String lastName;

    public ReviewDTO(Long userId, Long businessId, Double rating, String content, LocalDate reviewDate, String firstName, String lastName) {
        this.userId = userId;
        this.businessId = businessId;
        this.rating = rating;
        this.content = content;
        this.reviewDate = reviewDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ReviewDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
