package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;


    @GetMapping("/registration")
    public String registerPage(Model model){
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);
        return "login";
    }

}
