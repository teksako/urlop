package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.urlopy.dto.DepartmentDto;
import pl.sda.urlopy.dto.HolidayDto;
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

    @GetMapping({"/updateDepartment"})
    public String departmentUpdatePage(Model model) {
        model.addAttribute("role", userService.actualLoginUserRole());
        model.addAttribute("username", userService.userData(userService.findUserByUsername()));

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);

        model.addAttribute("department", new DepartmentDto());
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "updateDepartment";
    }

    @PostMapping("/updateDepartment")
    //@RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    public String saveUpdateDepartment(@ModelAttribute("department") DepartmentDto department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateDepartment";
        }

        departmentService.departmentUpdate(department);
        return "index";
    }
}
