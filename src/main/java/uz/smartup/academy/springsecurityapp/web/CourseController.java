package uz.smartup.academy.springsecurityapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.service.CourseService;
import uz.smartup.academy.springsecurityapp.service.InstructorService;

@Controller
@RequestMapping("/web/courses/")
public class CourseController {
    private final CourseService service;
    private final InstructorService instructorService;

    public CourseController(CourseService service, InstructorService instructorService) {
        this.service = service;
        this.instructorService = instructorService;
    }


    @GetMapping("")
    public String listAllCourses(Model model){
        model.addAttribute("courseDTO", service.getAllCourses());
        return "course/courses-form";
    }

    @GetMapping("{id}")
    public String getCourseStudents(@PathVariable int id, Model model) {
        model.addAttribute("studentDTO", service.getCourseStudentsById(id));
        return "course/course-student-form";
    }

    @GetMapping("edit/{id}")
    public String editCourseForm(@PathVariable int id, Model model) {
        model.addAttribute("courseDTO", service.getCourseById(id));
        //System.out.println(service.getCourseById(id));
        return "course/course-edit";
    }

    @PostMapping("{id}")
    public String updateCourse(@PathVariable int id,
                               @ModelAttribute("courseDTO") CourseDTO courseDTO,
                               Model model) {

        service.updateCourse(courseDTO,id);
        return "course/courses-form.html";
    }

    @GetMapping("{id}/delete")
    public String deleteCourse(@PathVariable int id) {
        service.deleteCourseById(id);
        return "redirect:/web/courses";
    }






}


