package uz.smartup.academy.springsecurityapp.service;

import uz.smartup.academy.springsecurityapp.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllRewiews();

    ReviewDTO getReviewsById(int id);

    void deleteReviewById(int id);
}
