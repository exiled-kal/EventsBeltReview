package com.houlder.eventsbeltreviewer.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.houlder.eventsbeltreviewer.models.Event;
import com.houlder.eventsbeltreviewer.services.EventService;

@Controller
public class Events {
	private final EventService eventService;
	
	public Events(EventService eventService) {
		this.eventService = eventService;
	}
	
    // -------------------- ROUTES FOR NEW EVENT -------------------- //
	
	@PostMapping("/event/new")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
		if(result.hasErrors()) {
			return "dashboard.jsp";
		} else {
			eventService.createEvent(event);
			return "redirect:/home";
		}
	}
	
	// -------------------- ROUTES TO FIND EVENT -------------------- //
	
    @RequestMapping("/event/{id}")
    public String showEvent(@PathVariable("id") Long id, Model model) {
        Event event = eventService.findEvent(id);
        model.addAttribute("event", event);
        return "show.jsp";
    }  
	
	
	// -------------------- ROUTES TO EDIT/UPDATE EVENT -------------------- //
	
	@RequestMapping("/event/{id}/edit")
	public String editEvent(@PathVariable("id") Long id, Model model) {
		Event event = eventService.findEvent(id);
		model.addAttribute("event", event);
		return "edit.jsp";
	}
	
	@PutMapping("/event/{id}")
    public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "edit.jsp";
        } else {
            eventService.updateEvent(event);
            return "redirect:/home";
        }
    }
	
	// -------------------- ROUTES TO DELETE/DESTROY EVENT -------------------- //
	
	@RequestMapping("/event/{id}/destroy")
	public String deleteEvent(@PathVariable("id") Long id, Model model) {
		eventService.deleteEvent(id);
		return "redirect:/home";
	}
	
    @DeleteMapping("/event/{id}")
    public String destroyEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return "redirect:/home";
    }

}