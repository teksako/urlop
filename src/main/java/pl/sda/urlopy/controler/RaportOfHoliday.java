package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.model.User;
import pl.sda.urlopy.repository.HolidayRepository;
import pl.sda.urlopy.service.ExportPdf;
import pl.sda.urlopy.service.HolidayService;
import pl.sda.urlopy.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RaportOfHoliday {
    private final HolidayService holidayService;
    private final UserService userService;

    @GetMapping({"/raport"})
    public String raportPage(Model model,UserDto userDto ) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
//        List<User> users = userService.loadUserByUsername();
//        model.addAttribute("firstname", users);
        List<Holiday> holidays = holidayService.findAllByActualLoggedUserIs();
        model.addAttribute("holidays", holidays);
        return "raportofholiday";
    }

    @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> holidayReports(HttpServletResponse response) throws IOException {

        List<Holiday> holidays = holidayService.findAllByActualLoggedUserIs();

        ByteArrayInputStream bis = ExportPdf.holidaysReport(holidays);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment;filename=raporturlopowy.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
