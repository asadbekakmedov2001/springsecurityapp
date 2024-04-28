package uz.smartup.academy.springsecurityapp.service;

import uz.smartup.academy.springsecurityapp.dto.InstructorDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;

import java.util.List;

public interface InstructorService {
    List<UserDTO> getAllInstructor();

    void createInstructor(InstructorDTO instructorDTO);

}
