package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.service.UserService;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class changePasswordController {

    private final UserService userService;


    @GetMapping("/changepassword")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDto());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String saveUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "changepassword";
        }

        userService.update(user);
        return "index";
    }

}
