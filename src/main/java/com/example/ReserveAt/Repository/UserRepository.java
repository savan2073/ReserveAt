package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}