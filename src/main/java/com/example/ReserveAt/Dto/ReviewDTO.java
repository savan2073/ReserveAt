package com.example.ReserveAt.Dto;

import java.time.LocalDate;

public class ReviewDTO {
    private Long userId;
    private Long businessId;
    private Double rating;
    private String content;
    private LocalDate reviewDate;

    public ReviewDTO(Long userId, Long businessId, Double rating, String content, LocalDate reviewDate) {
        this.userId = userId;
        this.businessId = businessId;
        this.rating = rating;
        this.content = content;
        this.reviewDate = reviewDate;
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
}
