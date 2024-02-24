package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.ReviewDTO;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.Review;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.BusinessRepository;
import com.example.ReserveAt.Repository.FriendshipRepository;
import com.example.ReserveAt.Repository.ReviewRepository;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewImplementation implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;

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

    @Override
    public List<ReviewDTO> findFriendsReviews(Long userId) {
        List<Long> friendsIds = friendshipRepository.findAllByUserUserId(userId).stream()
                .map(friendship -> friendship.getFriend().getUserId())
                .collect(Collectors.toList());

        // Użyj findAllByFriends do znalezienia recenzji napisanych przez znajomych
        List<Review> reviews = reviewRepository.findAllByFriends(friendsIds);

        // Konwersja recenzji na ReviewDTO
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(
                review.getUser().getUserId(),
                review.getBusiness().getBusinessId(),
                review.getRating(),
                review.getContent(),
                review.getReviewDate(),
                review.getUser().getFirstName(),
                review.getUser().getLastName()
        );
    }

    @Override
    public List<ReviewDTO> findByBusinessId(Long businessId) {
        List<Review> reviews =  reviewRepository.findByBusinessBusinessId(businessId);
        return reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
