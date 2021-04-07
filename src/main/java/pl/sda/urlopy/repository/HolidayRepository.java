package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.ui.Model;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.model.User;

import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("SELECT SUM(m.countOfDays) FROM Holiday m where m.accepted ='AKCEPTACJA' and  m.actualLoggedUser=?1 ")
    Long allDays(String owner);

    List<Holiday> findAllByAcceptedIsAndActualLoggedUserIsNot(String accept, String user);

    List<Holiday> findAllByActualLoggedUserIs(String owner);

    Optional<Holiday> findAllByActualLoggedUserIsAndAcceptedIs(String owner, String accepter);

    Long countHolidayByAcceptedIsAndActualLoggedUserIs(String accept, String user);

    Long countAllByActualLoggedUserIs(String user);
//   List<Holiday> findAllByActualLoggedUserIs();

    //List<Holiday> findAll(HolidayDto holidayDto);
}
