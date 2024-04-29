package uz.smartup.academy.springsecurityapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.springsecurityapp.dto.CourseDTO;
import uz.smartup.academy.springsecurityapp.dto.ReviewDTO;
import uz.smartup.academy.springsecurityapp.service.CourseService;
import uz.smartup.academy.springsecurityapp.service.InstructorService;
import uz.smartup.academy.springsecurityapp.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/web/students/")
public class StudentController {
    private final StudentService service;
    private final CourseService courseService;

    public StudentController(StudentService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @RequestMapping("")
    public String listAllIInstructor(Model model) {
        model.addAttribute("studentDTO", service.getAllInstructor());
        return "student/student-form.html";
    }

    int instid;
    @GetMapping("{id}/courses")
    public String getStudentCourses(@PathVariable int id, Model model) {
        model.addAttribute("courseDTO", service.getStudentCourses(id));
        instid=id;
        return "student/student-courses-form";
    }

    @GetMapping("{id}/courses/new")
    public String recordStudentCourseForm( Model model) {
        model.addAttribute("courseDTO", service.getCourseFilteredStudentById(instid));
        return "student/student-course-create.html";

    }

    @GetMapping("courses/{courseId}")
    public String saveStudentCourse(@PathVariable int courseId ,Model model) {
        service.enrollCourse(instid,courseId);

        List<CourseDTO> courseDTO1=service.getStudentCourses(courseId);

        model.addAttribute("courseDTO", courseDTO1);
        return "redirect:/web/students/";
    }


    int cID;
    @GetMapping("courses/{courseId}/reviews/new")
    public String getAllReviewsForCourseByStudent(@PathVariable int courseId, Model model) {
        cID = courseId;
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        System.out.println(instid+" "+courseId);
        List<ReviewDTO> reviews = service.getReviews(instid, courseId);
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("reviewDTO", reviews);
        return "student/student-course-review-form.html";
    }

    @GetMapping("courses/{courseId}/reviews")
    public String createStudentCourseReviewForm(@PathVariable int courseId, Model model){
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        ReviewDTO reviewDTO = new ReviewDTO();
        model.addAttribute("reviewDTO",reviewDTO);
        model.addAttribute("courseDTO",courseDTO);
        return "review/review-create.html";
    }

    @PostMapping("courses/{courseId}/reviews")
    public String addReviewToCourse(@PathVariable int courseId, @ModelAttribute ReviewDTO reviewDTO) {
        reviewDTO.setStudentId(reviewDTO.getStudentId());
        reviewDTO.setCourseId(reviewDTO.getCourseId());
        service.addReview(instid, courseId, reviewDTO);
        return "redirect:/web/students";
    }



}
