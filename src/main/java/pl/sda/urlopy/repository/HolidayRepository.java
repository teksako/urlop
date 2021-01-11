package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.urlopy.model.Holiday;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    List<Holiday> findAll();
    //List<Holiday> findAllByActualLoggedUser();
}
