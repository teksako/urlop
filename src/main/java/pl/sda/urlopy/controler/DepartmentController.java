package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.DepartmentDto;
import pl.sda.urlopy.service.DepartmentService;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/adddepartment")
    public String adddepartment() {
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
