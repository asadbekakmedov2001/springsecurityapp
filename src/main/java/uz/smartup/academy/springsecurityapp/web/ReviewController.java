package uz.smartup.academy.springsecurityapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.smartup.academy.springsecurityapp.service.ReviewService;

@Controller
@RequestMapping("/web/reviews/")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("")
    public String listAllReviews(Model model){
        model.addAttribute("reviewDTO", service.getAllRewiews());
        return "review/review-form.html";
    }

}
