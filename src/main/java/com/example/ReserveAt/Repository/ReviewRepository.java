package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Użyj nazwy pola encji 'business', a nie 'businessId' z nazwy kolumny w bazie danych
    int countByBusinessBusinessId(Long businessId);
}
