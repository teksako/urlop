package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.service.DepartmentService;
import pl.sda.urlopy.service.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserManagementController {

    private final UserService userService;
    private final DepartmentService departmentService;


    @GetMapping({"/deleteUserForm"})
    public String userForm(Model model) {
        model.addAttribute("user", new UserDto());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "deleteUserForm";
    }
//      INNA METODA
//    @GetMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable (value = "id") long id) {
//        userService.deleteUser(id);
//        return "deleteUserForm";
//    }

    @PostMapping("/deleteUser")
    public String saveDeleteUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "deleteUser";
        }
        userService.deleteUser(user);
        return "index";
    }

    @GetMapping({"/updateUserForm"})
    public String updateUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "updateUserForm";
    }

    @PostMapping("/updateUserForm")
    public String saveUpdateUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateUserForm";
        }
        userService.updateUser(user);
        return "index";
    }

    @GetMapping("/resetpassword")
    public String resetPasswordPage(Model model) {
        model.addAttribute("user", new UserDto());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "resetpassword";
    }

    @PostMapping("/resetpassword")
    public String savePasswordUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "resetpassword";
        }
        userService.resetPasswordByAdmin(user);
        return "index";
    }

    @GetMapping("/registration")
    public String registerPage(Model model) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());
        model.addAttribute("user", new UserDto());
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);
        return "index";
    }


}
