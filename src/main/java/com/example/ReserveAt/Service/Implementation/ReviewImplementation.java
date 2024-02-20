package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.ReviewDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.Review;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.BusinessRepository;
import com.example.ReserveAt.Repository.ReviewRepository;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReviewImplementation implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public Review addReview(ReviewDTO reviewDTO) {
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + reviewDTO.getUserId()));
        Business business = businessRepository.findById(reviewDTO.getBusinessId())
                .orElseThrow(() -> new EntityNotFoundException("Business not found with id: " + reviewDTO.getBusinessId()));

        Review review = new Review();
        review.setUser(user);
        review.setBusiness(business);
        review.setRating(reviewDTO.getRating());
        review.setContent(reviewDTO.getContent());
        review.setReviewDate(LocalDate.now());
        return reviewRepository.save(review);
    }
}
