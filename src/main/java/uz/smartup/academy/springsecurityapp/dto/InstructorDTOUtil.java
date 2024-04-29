package uz.smartup.academy.springsecurityapp.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.springsecurityapp.dao.AppDAO;
import uz.smartup.academy.springsecurityapp.entity.Instructor;
import uz.smartup.academy.springsecurityapp.entity.InstructorDetail;
import uz.smartup.academy.springsecurityapp.entity.User;
import uz.smartup.academy.springsecurityapp.service.UserService;

import java.util.List;

@Component
public class InstructorDTOUtil {

    private final AppDAO dao;

    public InstructorDTOUtil(AppDAO dao) {
        this.dao = dao;
    }


    public InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setUsername(instructor.getUser().getUsername());
        instructorDTO.setPassword(instructor.getUser().getPassword());
        instructorDTO.setFirstName(instructor.getUser().getFirstName());
        instructorDTO.setLastName(instructor.getUser().getLastName());
        instructorDTO.setEmail(instructor.getUser().getEmail());
        instructorDTO.setUserId(instructor.getUser().getId());

        // InstructorDetail ma'lumotlarini olib, InstructorDTO ga o'xshashlash
        InstructorDetail instructorDetail = instructor.getInstructorDetail();
        if (instructorDetail != null) {
            instructorDTO.setHobby(instructorDetail.getHobby());
            instructorDTO.setYoutubeChannel(instructorDetail.getYoutubeChannel());
        }

        return instructorDTO;
    }

    public Instructor toEntity(InstructorDTO dto) {


        int userId = dto.getUserId();
        System.out.println(dto);

        System.out.println(userId);

        User user = dao.findUserById(userId);

        Instructor instructor = new Instructor();

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby(dto.getHobby());
        instructorDetail.setYoutubeChannel(dto.getYoutubeChannel());

        instructor.setUser(user);
        instructor.setInstructorDetail(instructorDetail);

        return instructor;
    }
    public List<InstructorDTO> toEntities(List<Instructor> instructors) {
        return instructors.stream().map(this::toDTO).toList();
    }


}
