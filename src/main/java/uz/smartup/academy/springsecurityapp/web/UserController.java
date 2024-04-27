package uz.smartup.academy.springsecurityapp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.springsecurityapp.dto.UserDTO;
import uz.smartup.academy.springsecurityapp.dto.UserDTOUtil;
import uz.smartup.academy.springsecurityapp.entity.Role;
import uz.smartup.academy.springsecurityapp.entity.User;
import uz.smartup.academy.springsecurityapp.service.UserSer;
import uz.smartup.academy.springsecurityapp.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/web/users/")
public class UserController {

    @Value("${roles}")
    private Set<String> roles;

    private final UserService userService;
    private final UserSer userSer;
    private final UserDTOUtil userDTOUtil;

    public UserController(UserService userService, UserSer userSer, UserDTOUtil userDTOUtil) {
        this.userService = userService;
        this.userSer = userSer;
        this.userDTOUtil = userDTOUtil;
    }

    @RequestMapping("")
    public String listAllIUsers(Model model) {
        model.addAttribute("userDTO", userService.getAllUsers());
        return "user/user-form";
    }

    @GetMapping("register")
    public String createStudentForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO",userDTO);
        model.addAttribute("roles", roles);
        return "registr";
    }

    @PostMapping("register/save")
    public String saveStudent(@ModelAttribute UserDTO userDTO,  Set<Role> roles){
        User user = userDTOUtil.toEntity(userDTO);
        userSer.registerUser(user, roles);
        return "redirect:/web/users/";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("userDTO", userService.getUserById(id));
        //System.out.println(service.getCourseById(id));
        return "user/user-edit-form";
    }

    @PostMapping("{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute("userDTO")
                                   UserDTO userDTO,
                                   Model model) {
        UserDTO existUser = userService.getUserById(id);
        existUser.setId(userDTO.getId());
        existUser.setUsername(userDTO.getUsername());
        existUser.setPassword("{noop}"+userDTO.getPassword());
        existUser.setLastName(userDTO.getLastName());
        existUser.setFirstName(userDTO.getFirstName());
        existUser.setEnabled("Y");
        userService.updateUser(existUser);
        return "redirect:/web/users/";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/web/users/";
    }



}
