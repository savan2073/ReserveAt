package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}