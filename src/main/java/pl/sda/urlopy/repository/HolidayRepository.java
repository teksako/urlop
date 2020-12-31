package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.urlopy.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}
