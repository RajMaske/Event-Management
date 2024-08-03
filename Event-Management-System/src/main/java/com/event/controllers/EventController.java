package com.event.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.entity.Event;
import com.event.entity.Organizer;
import com.event.entity.Venue;
import com.event.services.EventService;
import com.event.services.OrganizerService;
import com.event.services.VenueService;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;
    private final OrganizerService organizerService;

    @Autowired
    public EventController(EventService eventService, VenueService venueService, OrganizerService organizerService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.organizerService = organizerService;
    }

    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event-details";
    }

    @GetMapping("/new")
    public String newEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("venues", venueService.getAllVenues());
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        return "new-event";
    }

    @PostMapping
    public String createEvent(@ModelAttribute Event event, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("venues", venueService.getAllVenues());
            model.addAttribute("organizers", organizerService.getAllOrganizers());
            return "new-event";
        }

        // Save the event
        eventService.saveEvent(event);
        return "redirect:/events";
    }


    @GetMapping("/edit/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("venues", venueService.getAllVenues());
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        return "edit-event";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event) {
        eventService.updateEvent(id, event);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/venue/{venueId}")
    public String getEventsByVenue(@PathVariable Long venueId, Model model) {
        Venue venue = venueService.getVenueById(venueId);
        List<Event> events = eventService.getEventsByVenue(venue);
        model.addAttribute("events", events);
        model.addAttribute("venue", venue);
        return "events-by-venue";
    }

    @GetMapping("/organizer/{organizerId}")
    public String getEventsByOrganizer(@PathVariable Long organizerId, Model model) {
        Organizer organizer = organizerService.getOrganizerById(organizerId);
        List<Event> events = eventService.getEventsByOrganizer(organizer);
        model.addAttribute("events", events);
        model.addAttribute("organizer", organizer);
        return "events-by-organizer";
    }
}
