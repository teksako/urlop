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
import pl.sda.urlopy.dto.DepartmentDto;
import pl.sda.urlopy.service.DepartmentService;
import pl.sda.urlopy.service.UserService;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UserService userService;

    @GetMapping("/adddepartment")
    public String addNewDepartment(Model model) {
//        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("username", principal.getUsername());
//        model.addAttribute("role", principal.getAuthorities());
        model.addAttribute("nameOfDepartment", new DepartmentDto());
        return "addDepartment";
    }

    @PostMapping("/adddepartment")
    public String saveDepartment(@ModelAttribute("department") DepartmentDto department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addDepartment";
        }
        departmentService.save(department);
        return "login";
    }
}
