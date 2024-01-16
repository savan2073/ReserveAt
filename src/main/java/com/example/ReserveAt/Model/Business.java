package com.example.ReserveAt.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "business")
@NoArgsConstructor
@AllArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "businessId")
    private Long businessId;
    @Column(name = "businessName",nullable = false)
    private String businessName;
    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;
    @Column(name = "address")
    private String address;
    @Column(name = "rating")
    private double rating;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "business")
    @JsonManagedReference//pozwala na serializację employees
    private List<Employee> employees;
    @Enumerated(EnumType.STRING)
    @Column(name = "businessType")
    private BusinessType businessType;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "business")
    private List<Review> reviews;
    @Column(name = "photoPath")
    private String photoPath;
    @OneToMany(mappedBy = "business")
    private List<WorkingHours> workingHours;

}
