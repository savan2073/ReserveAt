package com.example.ReserveAt.Dto;

import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Model.Review;
import jakarta.persistence.*;

import java.util.List;

public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private String address;
    private double rating;
    private String description;
    private List<Employee> employees;
    private BusinessType businessType;
    private String email;
    private String password;
    private List<Review> reviews;
    private String photoPath;
}
