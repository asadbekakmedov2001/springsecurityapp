package uz.smartup.academy.springsecurityapp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.springsecurityapp.dao.AppDAO;
import uz.smartup.academy.springsecurityapp.dto.*;
import uz.smartup.academy.springsecurityapp.entity.Course;
import uz.smartup.academy.springsecurityapp.entity.Instructor;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceimpl implements InstructorService{
    private final AppDAO appDAO;
    private final InstructorDTOUtil instructorDTOUtil;
    private final CourseDTOUtil courseDTOUtil;
    private final UserDTOUtil userDTOUtil;

    public InstructorServiceimpl(AppDAO appDAO, InstructorDTOUtil instructorDTOUtil, CourseDTOUtil courseDTOUtil, UserDTOUtil userDTOUtil) {
        this.appDAO = appDAO;
        this.instructorDTOUtil = instructorDTOUtil;
        this.courseDTOUtil = courseDTOUtil;
        this.userDTOUtil = userDTOUtil;
    }

    @Override
    public List<InstructorDTO> getAllInstructor() {
        List<Instructor> allInstructorDTOS = appDAO.getAllInstructors();
        return instructorDTOUtil.toEntities(allInstructorDTOS);
    }

    @Override
    public List<UserDTO> getAllInstructorRole() {
        List<User> allInstructor = appDAO.getAllInstructor();
        return userDTOUtil.toEntities(allInstructor);
    }

    @Override
    @Transactional
    public void createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorDTOUtil.toEntity(instructorDTO);
        appDAO.save(instructor);
    }


    @Override
    public InstructorDTO getInstructor(int id) {
        Instructor instructor = appDAO.findInstructorById(id);
        return instructorDTOUtil.toDTO(instructor);
    }

    @Override
    @Transactional
    public void updateInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorDTOUtil.toEntity(instructorDTO);
        appDAO.updateInstructor(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(int instructorId) {
        appDAO.deleteInstructor(instructorId);
    }


    @Override
    @Transactional
    public void addCourse(int id, CourseDTO courseDTO) {
        Course course = courseDTOUtil.toEntity(courseDTO);
        course.setInstructor(appDAO.findInstructorById(id));

        appDAO.saveCourse(course);
    }

    @Override
    public List<CourseDTO> getCourses(int id) {
        List<Course> courses = appDAO.getAllCourcesInstructorById(id);
        return courseDTOUtil.toDTOs(courses);
    }


}
