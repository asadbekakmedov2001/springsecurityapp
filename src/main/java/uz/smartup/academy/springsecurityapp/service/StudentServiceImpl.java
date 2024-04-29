package uz.smartup.academy.springsecurityapp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.springsecurityapp.dao.AppDAO;
import uz.smartup.academy.springsecurityapp.dto.*;
import uz.smartup.academy.springsecurityapp.entity.Course;
import uz.smartup.academy.springsecurityapp.entity.Instructor;
import uz.smartup.academy.springsecurityapp.entity.Review;
import uz.smartup.academy.springsecurityapp.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private final AppDAO appDAO;

    private final StudentDTOUtil studentDTOUtil;
    private final ReviewDTOUtil reviewDTOUtil;

    private final CourseDTOUtil courseDTOUtil;

    public StudentServiceImpl(AppDAO appDAO, StudentDTOUtil studentDTOUtil, ReviewDTOUtil reviewDTOUtil, CourseDTOUtil courseDTOUtil) {
        this.appDAO = appDAO;
        this.studentDTOUtil = studentDTOUtil;
        this.reviewDTOUtil = reviewDTOUtil;
        this.courseDTOUtil = courseDTOUtil;
    }

    @Override
    public List<StudentDTO> getAllInstructor() {
        List<Student> students = appDAO.getAllStudents();
        return studentDTOUtil.toDTOs(students);
    }

    @Override
    @Transactional
    public void enrollCourse(int id, int courseId) {
        appDAO.addCourseInStudent(id, courseId);
    }

    @Override
    public List<CourseDTO> getStudentCourses(int id) {
        List<Course> courses = appDAO.getStudentCourses(id);
        return courseDTOUtil.toDTOs(courses);
    }

    @Override
    public List<CourseDTO> getCourseFilteredStudentById(int id) {
        List<Course> courses = appDAO.getCoursesFilteredByStudent(id);
        return courseDTOUtil.toDTOs(courses);
    }

    @Override
    @Transactional
    public void excludeStudentFromCourse(int id, int courseId) {
        appDAO.deleteStudentFromCourse(id, courseId);
    }

    @Override
    @Transactional
    public void addReview(int id, int courseId, ReviewDTO review) {
        appDAO.addStudentReview(id, courseId, reviewDTOUtil.toEntity(review));
    }

    @Override
    public List<ReviewDTO> getReviews(int id, int courseId) {
        List<Review> reviews = appDAO.getStudentReviews(id, courseId);
        return reviewDTOUtil.toDTOs(reviews);
    }

    @Override
    @Transactional
    public void updateReview(int id, int courseId, ReviewDTO review) {
        appDAO.updateStudentReview(id, courseId, reviewDTOUtil.toEntity(review));
    }

}
