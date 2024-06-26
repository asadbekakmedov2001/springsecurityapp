package uz.smartup.academy.springsecurityapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.dto.InstructorDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;
import uz.smartup.academy.springsecurityapp.entity.Instructor;
import uz.smartup.academy.springsecurityapp.service.CourseService;
import uz.smartup.academy.springsecurityapp.service.InstructorService;
import uz.smartup.academy.springsecurityapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/web/instructors/")
public class InstructorController {
    private final InstructorService service;
    private final CourseService courseService;
    private final UserService userService;

    public InstructorController(InstructorService service, CourseService courseService, UserService userService) {
        this.service = service;
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping("")
    public String listAllIInstructor(Model model) {
        model.addAttribute("instructorDTO", service.getAllInstructor());
        return "instructor/instructor-form.html";
    }

    @RequestMapping("role")
    public String listAllIUsers(Model model) {
        model.addAttribute("instructorDTO", service.getAllInstructorRole());
        return "instructor/instructor-role-form.html";
    }
    int u_id;
    @GetMapping("detailcreate/{userId}")
    public String createDetail(@PathVariable int userId, Model model) {
        u_id=userId;
        UserDTO userDTO = userService.getUserById(userId);
        InstructorDTO instructorDTO= new InstructorDTO();

        model.addAttribute("instructorDTO", instructorDTO);
        model.addAttribute("userDTO", userDTO);
        return "instructor/instructor-create.html";
    }

    @PostMapping("new/")
    public String updateUser(@ModelAttribute("instructorDTO") InstructorDTO instructorDTO) {
        instructorDTO.setUserId(u_id);
        service.createInstructor(instructorDTO);
        return "redirect:/web/users/";
    }



    int ins_id;
    @GetMapping("edit/{id}")
    public String editInstructorForm(@PathVariable int id, Model model) {
        InstructorDTO instructorDTO = service.getInstructor(id);
        UserDTO userDTO = userService.getUserById(instructorDTO.getUserId());
        ins_id=id;

        model.addAttribute("instructorDTO", instructorDTO);
        model.addAttribute("userDTO", userDTO);
        return "instructor/instructor-edit";
    }

    @PostMapping("{id}")
    public String updateInstructor(@PathVariable int id,
                                   @ModelAttribute("instructorDTO") InstructorDTO instructorDTO,
                                   @ModelAttribute("userDTO")UserDTO userDTO) {

        InstructorDTO existingInstructorDTO = service.getInstructor(id);

        existingInstructorDTO.setId(id);

        existingInstructorDTO.setUserId(userDTO.getId());
        existingInstructorDTO.setUsername(userDTO.getUsername());
        existingInstructorDTO.setPassword(userDTO.getPassword());
        existingInstructorDTO.setFirstName(userDTO.getFirstName());
        existingInstructorDTO.setLastName(userDTO.getLastName());
        existingInstructorDTO.setEmail(userDTO.getEmail());

        existingInstructorDTO.setInstructorDetailId(existingInstructorDTO.getInstructorDetailId());
        existingInstructorDTO.setHobby(instructorDTO.getHobby());
        existingInstructorDTO.setYoutubeChannel(instructorDTO.getYoutubeChannel());

        System.out.println(existingInstructorDTO);

        service.updateInstructor(existingInstructorDTO);
        return "redirect:/web/instructors/";
    }

    @GetMapping("delete/{id}")
    public String deleteInstructor(@PathVariable int id) {
        service.deleteInstructor(id);
        return "redirect:/web/instructors";
    }

    int instid;

    @GetMapping("{id}/courses")
    public String listAllInstructorCourses(@PathVariable int id, Model model) {

        instid=id;
        List<CourseDTO> courseDTO=service.getCourses(instid);

        model.addAttribute("courseDTO", courseDTO);

        return "instructor/instructor-course-form";
    }

    @GetMapping("courses/new")
    public String createCourseForm( Model model) {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setInstructorId(instid);
        model.addAttribute("courseDTO", courseDTO);
        return "course/course-create";

    }

    @PostMapping("courses")
    public String saveInstructor(@ModelAttribute("courseDTO") CourseDTO courseDTO,Model model) {

        service.addCourse(instid,courseDTO);

        List<CourseDTO> courseDTO1=service.getCourses(instid);

        model.addAttribute("courseDTO", courseDTO1);
        return "instructor/instructor-course-form.html";
    }



}
