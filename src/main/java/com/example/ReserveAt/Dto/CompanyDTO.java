package com.example.ReserveAt.Dto;

import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.City;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Model.Review;
import jakarta.persistence.*;

import java.util.List;

public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private City city;
    private String address;
    private double rating;
    private String description;
    private List<Employee> employees;
    private BusinessType businessType;
    private String email;
    private String password;
    private List<Review> reviews;
    private String photoPath;

    public CompanyDTO(Long companyId, String companyName, City city, String address, double rating, String description, List<Employee> employees, BusinessType businessType, String email, String password, List<Review> reviews, String photoPath) {
        this.companyId = companyId;
        this.companyName = companyName;
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
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
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
}

