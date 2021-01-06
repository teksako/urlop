package pl.sda.urlopy.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.urlopy.service.UserService;


@Controller
public class MainController {

    @Autowired
    UserService userService;
    @GetMapping({"/", "/index"})
    public String mainPage(Model model, ModelMap modelMap) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
       model.addAttribute("role", principal.getAuthorities());
//        modelMap.put("products", userService2.findAll());
////        List<User> user = new ArrayList<>();
////        model.addAttribute("user", user);
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);

        return "index";
    }
}
