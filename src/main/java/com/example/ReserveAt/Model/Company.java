package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyId")
    private Long companyId;
    @Column(name = "companyName",nullable = false)
    private String companyName;
    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;
    @Column(name = "address")
    private String address;
    @Column(name = "rating")
    private double rating;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
    @Enumerated(EnumType.STRING)
    @Column(name = "businessType")
    private BusinessType businessType;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
    @Column(name = "photoPath")
    private String photoPath;

}
