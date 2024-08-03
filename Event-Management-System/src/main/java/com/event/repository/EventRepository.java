package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.entity.Event;
import com.event.entity.Organizer;
import com.event.entity.Venue;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByVenue(Venue venue);  // Add this method
    List<Event> findByOrganizerId(Long organizerId);
    List<Event> findByOrganizer(Organizer organizer);  // Add this method
}




