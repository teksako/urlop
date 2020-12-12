package pl.sda.urlopy.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import pl.sda.urlopy.dto.UserDto;

@Controller
public class RegistrationConroller {
    @GetMapping({"/registration"})
    public String registerPage(WebRequest request, Model model){
        UserDto userDto = new UserDto();
        return "registration";
    }
}
