package uz.smartup.academy.springsecurityapp.dao;

import uz.smartup.academy.springsecurityapp.entity.*;

import java.util.List;

public interface AppDAO {

    void saveUser(User user);

    List<User> getAllUsers();

    User findUserById(int id);

    User findUserByEmail(String email);

    void updateUser(User user);

    void deleteUserById(int id);

    void save(Instructor instructor);

    List<Instructor> getAllInstructors();

    Instructor findInstructorById(int id);

    void updateInstructor(Instructor instructor);

    void deleteInstructorById(int id);

    void saveCourse(Course course);

    List<Course> getAllCourcesInstructorById(int id);

    void saveStudent(Student student);

    List<Student> getAllStudents();

    Student findStudentById(int id);

    void updateStudent(Student student);

    void deleteStudentById(int id);

    void addCourseInStudent(int studentId, int courseId);

    List<Course> getStudentCourses(int id);

    void deleteStudentFromCourse(int studentId, int courseId);

    void addStudentReview(int studentId, int courseId, Review review);

    List<Review> getStudentReviews(int studentId, int courseId);

    void updateStudentReview(int studentId, int courseId, Review review);

    List<Course> getAllCourses();

    Course findCourseById(int id);

    List<Review> getReviewsByCourseId(int id);

    List<Student> getStudentsByCourseId(int id);

    void updateCourse(Course course);

    void deleteCourseById(int id);

    List<Review> getAllReviews();

    Review findReviewsById(int id);

    void deleteReviewById(int id);

    List<Course> getCoursesFilteredByStudent(int id);

}
