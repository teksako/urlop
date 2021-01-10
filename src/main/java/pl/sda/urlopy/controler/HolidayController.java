package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.service.HolidayService;
import pl.sda.urlopy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;
    private final UserService userService;

    @GetMapping({"/holiday"})
    public String holidayPage(Model model) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());
        model.addAttribute("holiday", new HolidayDto());
//      User u = principal.getUser(principal.getUsername());
//      request.getSession().setAttribute("userId", u.getId);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);


        return "holiday";
    }


    @PostMapping("/holiday")
    public String saveHoliday(@ModelAttribute("holiday") HolidayDto holiday, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "holiday";
        }
        holidayService.save(holiday);
        return "index";
    }

}
