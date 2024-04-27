package uz.smartup.academy.springsecurityapp.service;

import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.dto.ReviewDTO;
import uz.smartup.academy.springsecurityapp.dto.StudentDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(int id);

    List<ReviewDTO> getCourseReviewsById(int id);

    List<StudentDTO> getCourseStudentsById(int id);

    void updateCourse(CourseDTO courseDTO, int id);

    void deleteCourseById(int id);
}
