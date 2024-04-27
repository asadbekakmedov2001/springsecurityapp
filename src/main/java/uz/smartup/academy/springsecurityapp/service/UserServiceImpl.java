package uz.smartup.academy.springsecurityapp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.springsecurityapp.dao.AppDAO;
import uz.smartup.academy.springsecurityapp.dto.CourseDTOUtil;
import uz.smartup.academy.springsecurityapp.dto.InstructorDTOUtil;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTOUtil;
import uz.smartup.academy.springsecurityapp.entity.Role;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final AppDAO dao;

    private final UserDTOUtil dtoUtil;

    public UserServiceImpl(AppDAO dao, UserDTOUtil dtoUtil) {
        this.dao = dao;
        this.dtoUtil = dtoUtil;
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User>users=dao.getAllUsers();
        return dtoUtil.toEntities(users);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = dao.findUserById(id);
        return dtoUtil.toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = dao.findUserByEmail(email);
        return dtoUtil.toDTO(user);
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = dtoUtil.toEntity(userDTO);
        dao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }
}
