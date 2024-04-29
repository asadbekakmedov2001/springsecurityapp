package uz.smartup.academy.springsecurityapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import uz.smartup.academy.springsecurityapp.entity.*;

import java.util.List;
@Repository
public class AppDAOImpl implements AppDAO{

    private final EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User>query= entityManager.createQuery("From User", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) {
        return entityManager.find(User.class, email);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserById(int id) {
        User user = findUserById(id);
        entityManager.remove(user);
    }

    @Override
    public void addUserinRole(int userId, int roleId) {
        User user = findUserById(userId);
        Role role = entityManager.find(Role.class,roleId);
        user.addRole(role);
        updateUser(user);
    }

    @Override
    public List<Role> getRolesFilteredByUser(int id) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE NOT EXISTS (SELECT 1 FROM UsersRoles ur WHERE ur.role.id = r.id and ur.user.id = :id)"
                , Role.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


    @Override
    public List<Role> getUserRoles(int id) {
        User user = findUserById(id);

        TypedQuery<Role> query = entityManager.createQuery("FROM Role WHERE :user MEMBER OF users", Role.class);
        query.setParameter("user", user);

        return query.getResultList();
    }

    @Override
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public List<User> getAllInstructor() {
        String hql = "SELECT u FROM User u " +
                "WHERE u.id IN (SELECT ur.user.id FROM UsersRoles ur JOIN ur.role r WHERE r.name = :roleName) " +
                "AND u.id NOT IN (SELECT i.user.id FROM Instructor i)";

        return entityManager.createQuery(hql, User.class)
                .setParameter("roleName", "ROLE_INSTRUCTOR")
                .getResultList();
    }

    @Override
    public List<Instructor> getAllInstructors() {
        TypedQuery<Instructor>query= entityManager.createQuery("From Instructor", Instructor.class);
        return query.getResultList();
    }


    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    public void updateInstructor(Instructor instructor) {

        entityManager.merge(instructor);
    }

    @Override
    public void deleteInstructor(int instructorId) {

    }



    @Override
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourcesInstructorById(int id) {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id = :id", Course.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student>query = entityManager.createQuery("From Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = findStudentById(id);
        entityManager.remove(student);
    }

    @Override
    public void addCourseInStudent(int studentId, int courseId) {
        Student student =findStudentById(studentId);
        Course course =entityManager.find(Course.class, courseId);

        student.addCourse(course);

        updateStudent(student);
    }

    @Override
    public List<Course> getStudentCourses(int id) {
        Student student = findStudentById(id);

        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE :student MEMBER OF students", Course.class);
        query.setParameter("student", student);

        return query.getResultList();
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        Student student =findStudentByIdJoinFetchCourses(studentId);
        student.removeCourseById(courseId);

        updateStudent(student);
    }

    private Student findStudentByIdJoinFetchCourses(int id) {
        TypedQuery<Student> query = entityManager.createQuery("""
                FROM Student s
                JOIN FETCH s.courses
                WHERE s.id = :id
                """, Student.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public void addStudentReview(int studentId, int courseId, Review review) {
        review.setStudentId(studentId);
        review.setCourseId(courseId);
        entityManager.persist(review);
    }

    @Override
    public List<Review> getStudentReviews(int studentId, int courseId) {
        TypedQuery<Review> query = entityManager.createQuery("""
                 FROM Review
                WHERE studentId = :studentId
                  AND courseId = :courseId
                 """, Review.class);

        query.setParameter("studentId", studentId);
        query.setParameter("courseId", courseId);

        return query.getResultList();
    }

    @Override
    public void updateStudentReview(int studentId, int courseId, Review review) {
        review.setStudentId(studentId);
        review.setCourseId(courseId);
        entityManager.merge(review);
    }

    @Override
    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course", Course.class);
        return query.getResultList();
    }

    @Override
    public Course findCourseById(int id) {
        Course course=entityManager.find(Course.class, id);
        return course;
    }

    @Override
    public List<Course> getCoursesFilteredByStudent(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE NOT EXISTS (SELECT 1 FROM CourseStudent cs WHERE cs.student.id = :id and cs.course.id = c.id)"
                , Course.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Review> getReviewsByCourseId(int id) {
        TypedQuery<Review> query = entityManager.createQuery("FROM Review WHERE courseId = :id", Review.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Student> getStudentsByCourseId(int id) {
        Course course = findCourseById(id);
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE :course MEMBER OF courses", Student.class);
        query.setParameter("course", course);

        return query.getResultList();
    }

    @Override
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = findCourseById(id);
        entityManager.remove(course);
    }

    @Override
    public List<Review> getAllReviews() {
        TypedQuery<Review> query = entityManager.createQuery("FROM Review", Review.class);
        return query.getResultList();
    }

    @Override
    public Review findReviewsById(int id) {
        return entityManager.find(Review.class,id);
    }

    @Override
    public void deleteReviewById(int id) {
        Review review = findReviewsById(id);
        entityManager.remove(review);
    }


}
