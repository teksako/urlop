package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.model.Location;
import pl.sda.urlopy.repository.LocationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class LocationService {
    @Autowired
    private final LocationRepository locationRepository;


    public void save(Location location) {
        locationRepository.save(location);
    }


    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
