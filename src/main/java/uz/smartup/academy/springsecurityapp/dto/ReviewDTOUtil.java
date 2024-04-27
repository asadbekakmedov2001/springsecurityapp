package uz.smartup.academy.springsecurityapp.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.springsecurityapp.entity.Review;

import java.util.List;

@Component
public class ReviewDTOUtil {
    public ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCourseId(review.getCourseId());
        dto.setStudentId(review.getStudentId());
        return dto;
    }

    public Review toEntity(ReviewDTO dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCourseId(dto.getCourseId());
        review.setStudentId(dto.getStudentId());
        return review;
    }
    public List<ReviewDTO> toDTOs(List<Review> reviews) {
        return reviews.stream().map(this::toDTO).toList();
    }
}
