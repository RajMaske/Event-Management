package com.event.services;

import java.util.List;

import com.event.entity.Venue;

public interface VenueService {
    List<Venue> getAllVenues();
    Venue getVenueById(Long id);
    void createVenue(Venue venue);
    void updateVenue(Long id, Venue updatedVenue);
    void deleteVenue(Long id);
}
