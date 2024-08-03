package com.event.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.event.entity.Venue;
import com.event.services.VenueService;

@Controller
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public String listVenues(Model model) {
        List<Venue> venues = venueService.getAllVenues();
        model.addAttribute("venues", venues);
        return "venues";
    }

    @GetMapping("/{id}")
    public String venueDetails(@PathVariable Long id, Model model) {
        Venue venue = venueService.getVenueById(id);
        model.addAttribute("venue", venue);
        return "venue-details";
    }

    @GetMapping("/new")
    public String newVenueForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "new-venue";
    }

    @PostMapping
    public String createVenue(@ModelAttribute Venue venue) {
        venueService.createVenue(venue);
        return "redirect:/venues";
    }

    @GetMapping("/edit/{id}")
    public String editVenueForm(@PathVariable Long id, Model model) {
        Venue venue = venueService.getVenueById(id);
        model.addAttribute("venue", venue);
        return "edit-venue";
    }

    @PostMapping("/update/{id}")
    public String updateVenue(@PathVariable Long id, @ModelAttribute Venue venue) {
        venueService.updateVenue(id, venue);
        return "redirect:/venues";
    }

    @GetMapping("/delete/{id}")
    public String deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return "redirect:/venues";
    }
}

