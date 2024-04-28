package uz.smartup.academy.springsecurityapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String listAllIUsers(Model model) {
        model.addAttribute("instructorDTO", service.getAllInstructor());
        return "instructor/instructor-form.html";
    }
    int u_id;
    @GetMapping("detailcreate/{userId}")
    public String createDetail(@PathVariable int userId, Model model) {
        System.out.println(userId);
        u_id=userId;
        UserDTO userDTO = userService.getUserById(userId);
        System.out.println(userDTO);
        InstructorDTO instructorDTO= new InstructorDTO();

        System.out.println(instructorDTO.getUserId());
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



}
