package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.model.User;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    List<Holiday> findAll();
    List<Holiday> findAllByAcceptedIs(String accept);
    List<Holiday> findAllByActualLoggedUserIs(String owner);

}
