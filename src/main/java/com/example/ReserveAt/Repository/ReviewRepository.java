package com.example.ReserveAt.Repository;

import com.example.ReserveAt.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Użyj nazwy pola encji 'business', a nie 'businessId' z nazwy kolumny w bazie danych
    int countByBusinessBusinessId(Long businessId);

    List<Review> findByBusinessBusinessId(Long businessId);

    @Query("SELECT r FROM Review r WHERE r.user.userId IN :friendIds")
    List<Review> findAllByFriends(@Param("friendIds") List<Long> friendIds);

}
