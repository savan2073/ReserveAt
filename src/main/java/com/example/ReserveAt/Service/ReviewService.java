package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.ReviewDTO;
import com.example.ReserveAt.Model.Review;

public interface ReviewService {

    Review addReview(ReviewDTO reviewDTO);
}
