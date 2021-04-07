package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChangePasswordController {

        private final UserService userService;


        @GetMapping("/changePassword")
        public String changePassword(Model model) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("username", principal.getUsername());
            model.addAttribute("role", principal.getAuthorities());
            model.addAttribute("user", new UserDto());
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
            return "changePassword";
        }

//    @PostMapping("/user/updatePassword")
//    @PreAuthorize("hasRole('READ_PRIVILEGE')")
//    public GenericResponse changeUserPassword(Locale locale,
//                                              @RequestParam("password") String password,
//                                              @RequestParam("oldpassword") String oldPassword) {
//        User user = userService.findUserByEmail(
//                SecurityContextHolder.getContext().getAuthentication().getName());
//
//        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
//            throw new InvalidOldPasswordException();
//        }
//        userService.changeUserPassword(user, password);
//        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
//    }
}
