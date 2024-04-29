package uz.smartup.academy.springsecurityapp.service;

import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.dto.InstructorDTO;
import uz.smartup.academy.springsecurityapp.dto.ReviewDTO;
import uz.smartup.academy.springsecurityapp.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllInstructor();

    void enrollCourse(int id, int courseId);

    List<CourseDTO> getStudentCourses(int id);

    List<CourseDTO> getCourseFilteredStudentById(int id);

    void excludeStudentFromCourse(int id, int courseId);

    void addReview(int id, int courseId, ReviewDTO review);

    List<ReviewDTO> getReviews(int id, int courseId);

    void updateReview(int id, int courseId, ReviewDTO review);

}
