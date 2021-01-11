package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.service.DepartmentService;
import pl.sda.urlopy.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final DepartmentService departmentService;


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
