package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.model.Location;
import pl.sda.urlopy.repository.LocationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class LocationService {
    private final LocationRepository locationRepository;
    private final Location location;

    public Location save(){
        return locationRepository.save(location);
    }

    public List<Location> findAll(){
        return locationRepository.findAll();
    }
}
