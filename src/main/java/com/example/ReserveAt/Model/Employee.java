package com.example.ReserveAt.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "employeeId")
    private Long employeeId;
    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "employeeSurname")
    private String employeeSurname;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "businessId")
    @JsonBackReference//zapobieganie serializacji business
    private Business business;
}
