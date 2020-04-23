package com.houlder.eventsbeltreviewer.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.houlder.eventsbeltreviewer.models.Event;
import com.houlder.eventsbeltreviewer.services.EventService;

@Controller
public class Events {
	private final EventService eventService;
	
	public Events(EventService eventService) {
		this.eventService = eventService;
	}
	
	@PostMapping("/event/new")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
		if(result.hasErrors()) {
			return "dashboard.jsp";
		} else {
			eventService.createEvent(event);
			return "redirect:/home";
		}
	}
	
	
}
