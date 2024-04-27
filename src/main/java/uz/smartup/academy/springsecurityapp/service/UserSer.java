package uz.smartup.academy.springsecurityapp.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.springsecurityapp.dao.UserRepository;
import uz.smartup.academy.springsecurityapp.entity.Role;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.Set;
@Service
public class UserSer {
    private final UserRepository userRepository;

    public UserSer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(User user, Set<Role> roles) {
        for (Role role : roles) {
            role.setUsername(user.getUsername());
        }

        user.setRoles(roles);

        userRepository.save(user);
    }
}
