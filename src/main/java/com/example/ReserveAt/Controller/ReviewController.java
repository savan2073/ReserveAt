package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.ReviewDTO;
import com.example.ReserveAt.Model.Review;
import com.example.ReserveAt.Model.User;
import com.example.ReserveAt.Repository.UserRepository;
import com.example.ReserveAt.Service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        reviewDTO.setUserId(user.getUserId());

        Review review = reviewService.addReview(reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/friends")
    public ResponseEntity<List<ReviewDTO>> getFriendsReviews(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        List<ReviewDTO> friendsReviews = reviewService.findFriendsReviews(user.getUserId());
        return ResponseEntity.ok(friendsReviews);
    }
}
