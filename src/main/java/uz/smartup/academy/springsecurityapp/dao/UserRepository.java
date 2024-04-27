package uz.smartup.academy.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.smartup.academy.springsecurityapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
