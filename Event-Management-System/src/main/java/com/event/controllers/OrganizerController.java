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

import com.event.entity.Organizer;
import com.event.services.OrganizerService;

@Controller
@RequestMapping("/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public String listOrganizers(Model model) {
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("organizers", organizers);
        return "organizers";
    }

    @GetMapping("/{id}")
    public String organizerDetails(@PathVariable Long id, Model model) {
        Organizer organizer = organizerService.getOrganizerById(id);
        model.addAttribute("organizer", organizer);
        return "organizer-details";
    }

    @GetMapping("/new")
    public String newOrganizerForm(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "new-organizer";
    }

    @PostMapping
    public String createOrganizer(@ModelAttribute Organizer organizer) {
        organizerService.createOrganizer(organizer);
        return "redirect:/organizers";
    }

    @GetMapping("/edit/{id}")
    public String editOrganizerForm(@PathVariable Long id, Model model) {
        Organizer organizer = organizerService.getOrganizerById(id);
        model.addAttribute("organizer", organizer);
        return "edit-organizer";
    }

    @PostMapping("/update/{id}")
    public String updateOrganizer(@PathVariable Long id, @ModelAttribute Organizer organizer) {
        organizer.setId(id);
        organizerService.updateOrganizer(organizer);
        return "redirect:/organizers";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return "redirect:/organizers";
    }
}
