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
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.Location;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.service.DepartmentService;
import pl.sda.urlopy.service.LocationService;
import pl.sda.urlopy.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UpdateDepartment {
    private final DepartmentService departmentService;
    private final LocationService locationService;
    private final UserService userService;

    @GetMapping("/updateDepartment")
    public String department(Model model) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());

       // model.addAttribute("department", new DepartmentDto());
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
//        List<Location> locations = locationService.findAll();
//        model.addAttribute("locations", locations);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "updateDepartment";
    }

    @PostMapping("/updateDepartment")
    public String saveDepartment(@ModelAttribute("department") DepartmentDto department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateDepartment";
        }
        departmentService.departmentUpdate(department);
        return "index";
    }
}
