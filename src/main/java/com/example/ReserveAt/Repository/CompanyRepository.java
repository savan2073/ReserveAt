package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findBizByEmailAndPassword(String email, String password);

    Company findByEmail(String email);
}
