package pl.sda.urlopy.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.repository.UserRepository;
import pl.sda.urlopy.service.UserService;


@Controller
public class MainController {

    @Autowired
    UserService userService;

    UserRepository userRepository;

    @GetMapping({"/", "/index"})
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());

        //  User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //   User user = (User) userRepository.findUserById();


        return "index";
    }
}
