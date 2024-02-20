package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.ReviewDTO;
import com.example.ReserveAt.Model.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> findFriendsReviews(Long userId);

    ReviewDTO convertToDTO(Review review);
}
