package uz.smartup.academy.springsecurityapp.service;

import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.dto.InstructorDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;

import java.util.List;

public interface InstructorService {

    List<InstructorDTO> getAllInstructor();

    List<UserDTO> getAllInstructorRole();

    void createInstructor(InstructorDTO instructorDTO);

    InstructorDTO getInstructor(int id);

    void updateInstructor(InstructorDTO instructorDTO);

    void deleteInstructor(int id);

    void addCourse(int id, CourseDTO courseDTO);

    List<CourseDTO> getCourses(int id);

}
