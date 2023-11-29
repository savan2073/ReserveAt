package com.example.ReserveAt.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userId;
    @Column(name = "firstName", length = 20)
    private String firstName;
    @Column(name = "lastName", length = 30)
    private String lastName;
    @Column(name = "email", length = 40)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber", length = 9)
    private String phoneNumber;
}