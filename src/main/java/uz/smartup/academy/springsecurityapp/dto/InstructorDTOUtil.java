package uz.smartup.academy.springsecurityapp.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.springsecurityapp.entity.Instructor;
import uz.smartup.academy.springsecurityapp.entity.InstructorDetail;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;

@Component
public class InstructorDTOUtil {
    public InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setUsername(instructor.getUser().getUsername());
        instructorDTO.setPassword(instructor.getUser().getPassword());
        instructorDTO.setFirstName(instructor.getUser().getFirstName());
        instructorDTO.setLastName(instructor.getUser().getLastName());
        instructorDTO.setEmail(instructor.getUser().getEmail());
        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby(instructorDTO.getHobby());
        instructorDetail.setYoutubeChannel(instructorDTO.getYoutubeChannel());

        instructor.setInstructorDetail(instructorDetail);

        return instructorDTO;
    }

    public Instructor toEntity(InstructorDTO dto) {
        Instructor instructor = new Instructor();
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        dto.setHobby(instructor.getInstructorDetail().getHobby());
        dto.setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());

        instructor.setUser(user);
        return instructor;
    }

    public List<InstructorDTO> toEntities(List<Instructor> instructors) {
        return instructors.stream().map(this::toDTO).toList();
    }
}
