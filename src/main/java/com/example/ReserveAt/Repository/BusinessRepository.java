package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.BusinessType;
import com.example.ReserveAt.Model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findBizByEmailAndPassword(String email, String password);

    Optional<Business> findByEmail(String email);

    Page<Business> findByCityAndBusinessType(City city, BusinessType businessType, Pageable pageable);

    Optional<Business> findByBusinessNameAndCity(String businessName, City city);
}
