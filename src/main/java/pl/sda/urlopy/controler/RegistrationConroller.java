package pl.sda.urlopy.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationConroller {
    @GetMapping({"/register"})
    public String registerPage(){
        return "register";
    }
}
