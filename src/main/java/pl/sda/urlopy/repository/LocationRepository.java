package pl.sda.urlopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.urlopy.model.Location;


import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
}
