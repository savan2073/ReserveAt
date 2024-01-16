package com.example.ReserveAt.Dto;

import com.example.ReserveAt.Model.*;

import java.util.List;

public class BusinessDTO {
    private Long businessId;
    private String businessName;
    private City city;
    private String address;
    private double rating;
    private String description;
    private List<EmployeeDTO> employees;
    private BusinessType businessType;
    private String email;
    private String password;
    private List<Review> reviews;
    private String photoPath;
    private int reviewCount;
    private List<WorkingHoursDTO> workingHours;

    public BusinessDTO(Long businessId, String businessName, City city, String address, double rating, String description, List<EmployeeDTO> employees, BusinessType businessType, String email, String password, List<Review> reviews, String photoPath, int reviewCount, List<WorkingHoursDTO> workingHours) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.description = description;
        this.employees = employees;
        this.businessType = businessType;
        this.email = email;
        this.password = password;
        this.reviews = reviews;
        this.photoPath = photoPath;
        this.reviewCount = reviewCount;
        this.workingHours = workingHours;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<WorkingHoursDTO> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHoursDTO> workingHours) {
        this.workingHours = workingHours;
    }
}

