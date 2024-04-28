package uz.smartup.academy.springsecurityapp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.springsecurityapp.dao.AppDAO;
import uz.smartup.academy.springsecurityapp.dto.InstructorDTO;
import uz.smartup.academy.springsecurityapp.dto.InstructorDTOUtil;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTOUtil;
import uz.smartup.academy.springsecurityapp.entity.Instructor;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;
@Service
public class InstructorServiceimpl implements InstructorService{
    private final AppDAO appDAO;
    private final InstructorDTOUtil instructorDTOUtil;
    private final UserDTOUtil userDTOUtil;

    public InstructorServiceimpl(AppDAO appDAO, InstructorDTOUtil instructorDTOUtil, UserDTOUtil userDTOUtil) {
        this.appDAO = appDAO;
        this.instructorDTOUtil = instructorDTOUtil;
        this.userDTOUtil = userDTOUtil;
    }

    @Override
    public List<UserDTO> getAllInstructor() {
        List<User> allInstructor = appDAO.getAllInstructor();
        return userDTOUtil.toEntities(allInstructor);
    }

    @Override
    @Transactional
    public void createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorDTOUtil.toEntity(instructorDTO);
        appDAO.save(instructor);
    }
}
