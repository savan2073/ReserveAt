package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "serviceprovider")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "providerId")
    private int providerId;
    @Column(name = "providerName")
    private String providerName;
    @Column(name = "address")
    private String address;
    @Column(name = "rating")
    private double rating;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "serviceProvider")
    private List<Employee> employees;
}
