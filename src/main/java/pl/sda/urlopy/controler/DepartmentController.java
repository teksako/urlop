package pl.sda.urlopy.controler;

import org.springframework.web.bind.annotation.GetMapping;

public class DepartmentController {
    @GetMapping("/adddepartment")
    public String login() {
        return "addDepartment";
    }
}
