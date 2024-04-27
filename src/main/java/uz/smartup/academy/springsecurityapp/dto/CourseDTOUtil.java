package uz.smartup.academy.springsecurityapp.dto;


import org.springframework.stereotype.Component;
import uz.smartup.academy.springsecurityapp.entity.Course;

import java.util.List;

@Component
public class CourseDTOUtil {
    public Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());

        return course;
    }

    public CourseDTO toDTO(Course course){

        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setInstructorId(course.getInstructor().getId());
        return courseDTO;
    }


    public List<CourseDTO> toDTOs(List<Course> courses) {
        return courses.stream().map(this::toDTO).toList();
    }
}
