package com.event.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.entity.Organizer;
import com.event.repository.OrganizerRepository;
import com.event.services.OrganizerService;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    @Override
    public Organizer getOrganizerById(Long id) {
        Optional<Organizer> organizer = organizerRepository.findById(id);
        return organizer.orElse(null);
    }

    @Override
    public void createOrganizer(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    @Override
    public void updateOrganizer(Organizer organizer) {
        // Implementation to save the organizer
        organizerRepository.save(organizer);
    }


    @Override
    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }
}

