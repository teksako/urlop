package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.repository.UserRepository;
import pl.sda.urlopy.service.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final UserService userService;


    @GetMapping({"/", "/index"})
    public String mainPage(Model model) {
        List<User> users = userService.findAll();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());
        return "index";
    }
}
