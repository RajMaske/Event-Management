package com.event.services;

import java.util.List;

import com.event.entity.Organizer;

public interface OrganizerService {
    List<Organizer> getAllOrganizers();
    Organizer getOrganizerById(Long id);
    void createOrganizer(Organizer organizer);
    void updateOrganizer(Organizer organizer); 
    void deleteOrganizer(Long id);
}

