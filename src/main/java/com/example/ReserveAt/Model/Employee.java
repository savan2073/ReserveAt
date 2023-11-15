package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private Long employeeId;
    @Column(name = "employeeName")
    private String name;
    @Column(name = "employeeSurname")
    private String surname;
    @OneToMany(mappedBy = "employee")
    private List<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "providerID")
    private ServiceProvider serviceProvider;
}
