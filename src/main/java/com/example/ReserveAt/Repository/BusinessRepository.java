package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findBizByEmailAndPassword(String email, String password);

    Optional<Business> findByEmail(String email);
}
