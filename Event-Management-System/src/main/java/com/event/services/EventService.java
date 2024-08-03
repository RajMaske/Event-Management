package com.event.services;

import java.util.List;

import com.event.entity.Event;
import com.event.entity.Organizer;
import com.event.entity.Venue;

public interface EventService {
    Event saveEvent(Event event);
    List<Event> getAllEvents();
    Event getEventById(Long id);
    void deleteEvent(Long id);
    Event updateEvent(Long id, Event event);
    List<Event> getEventsByVenue(Venue venue);  // Change method signature
    List<Event> getEventsByOrganizer(Long organizerId);
    List<Event> getEventsByOrganizer(Organizer organizer); 
}


