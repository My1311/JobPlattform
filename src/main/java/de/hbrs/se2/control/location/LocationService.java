package de.hbrs.se2.control.location;

import de.hbrs.se2.model.location.Location;
import de.hbrs.se2.model.location.LocationRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public @Nullable Location addLocation(Location location) {
        return this.locationRepository.save(location);
    }

    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    public void delete(Location location) {
        this.locationRepository.delete(location);
    }
}
