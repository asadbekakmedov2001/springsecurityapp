package uz.smartup.academy.springsecurityapp.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.springsecurityapp.entity.Student;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;

@Component
public class StudentDTOUtil {
    public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setUsername(student.getUser().getUsername());
        dto.setPassword(dto.getUsername());
        dto.setFirstName(student.getUser().getFirstName());
        dto.setLastName(student.getUser().getLastName());
        dto.setEmail(student.getUser().getEmail());
        return dto;

    }

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        student.setUser(user);
        return student;
    }
    public List<StudentDTO> toDTOs(List<Student> students) {
        return students.stream().map(this::toDTO).toList();
    }
}
