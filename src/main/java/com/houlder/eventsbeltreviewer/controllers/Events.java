package com.houlder.eventsbeltreviewer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.houlder.eventsbeltreviewer.models.Event;
import com.houlder.eventsbeltreviewer.models.User;
import com.houlder.eventsbeltreviewer.services.EventService;
import com.houlder.eventsbeltreviewer.services.UserService;

@Controller
public class Events {
	private final EventService eventService;
	private final UserService userService;
	
	public Events(EventService eventService, UserService userService) {
		this.eventService = eventService;
		this.userService = userService;
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
	
    @GetMapping("/event/{id}")
    public String showEvent(@PathVariable("id") Long id, Model model) {
        Event event = eventService.findEvent(id);
        List<User> allAttendees = userService.getAllUsers();
        model.addAttribute("event", event);
        model.addAttribute("allAttendees", allAttendees);
        return "showEvent.jsp";
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
    
    
	// -------------------- ADD USER AS ATTENDEE TO EVENT -------------------- //
    
	@PostMapping("/event/{id}/attendee")
	public String addAttendeeToEvent(@PathVariable("id")Long eventId, @RequestParam("user") Long attendeeId) {
		Event event = eventService.findEvent(eventId);
		User attendee = userService.findUser(attendeeId);
		event.getAttendees().add(attendee);
		eventService.createEvent(event);
		return "redirect:/home";
	}

	// -------------------- REMOVE USER AS ATTENDEE TO EVENT -------------------- //
	
	@DeleteMapping("/event/{id}/attendee")
	public String removeAttendeeFromEvent(@PathVariable("id")Long eventId, @RequestParam("user") Long attendeeId) {
		Event event = eventService.findEvent(eventId);
		User attendee = userService.findUser(attendeeId);
		event.getAttendees().remove(attendee);
		eventService.createEvent(event);
		return "redirect:/home";
	}
}