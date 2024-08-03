package com.event.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.entity.Venue;
import com.event.repository.VenueRepository;
import com.event.services.VenueService;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    @Override
    public Venue getVenueById(Long id) {
        Optional<Venue> venue = venueRepository.findById(id);
        return venue.orElse(null);
    }

    @Override
    public void createVenue(Venue venue) {
        venueRepository.save(venue);
    }

    @Override
    public void updateVenue(Long id, Venue updatedVenue) {
        if (venueRepository.existsById(id)) {
            updatedVenue.setId(id);
            venueRepository.save(updatedVenue);
        }
    }

    @Override
    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}
