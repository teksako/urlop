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
import pl.sda.urlopy.service.LocationService;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping({"/location"})
    public String locationPage(Model model){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());
        model.addAttribute("holiday", new HolidayDto());
        return "location";
    }

    @PostMapping("/location")
    public String saveLocation(@ModelAttribute("location") LocationService location, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "holiday";
        }
        locationService.save();
        return "login";
    }

}
