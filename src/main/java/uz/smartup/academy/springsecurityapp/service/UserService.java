package uz.smartup.academy.springsecurityapp.service;

import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.dto.RoleDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;
import uz.smartup.academy.springsecurityapp.entity.Role;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO getUserByEmail(String email);

    void updateUser(UserDTO userDTO);

    void deleteUserById(int id);

    void addRole(int userId, int roleId);

    List<RoleDTO> getRolesFilteredByUserId(int id);

}
