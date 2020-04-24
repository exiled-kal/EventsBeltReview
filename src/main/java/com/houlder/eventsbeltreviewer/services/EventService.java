package com.houlder.eventsbeltreviewer.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.houlder.eventsbeltreviewer.models.Event;
import com.houlder.eventsbeltreviewer.repositories.EventRepository;

@Service
public class EventService {
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	// CREATE AN EVENT
	public void createEvent(@Valid Event event) {
		eventRepository.save(event);
	}
	// RETRIEVE ALL EVENTS
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}
	// RETRIEVE ALL EVENTS BY STATE
	public List<Event> getAllEventsByState(String state) {
		return eventRepository.findByState(state);
	}
	// RETRIEVE ALL EVENTS OUTSIDE OF A STATE
	public List<Event> getAllEventsOutsideState(String state) {
		return eventRepository.findByStateNot(state);
	}
	// RETRIEVE AN EVENT
	public Event findEvent(Long id) {
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if(optionalEvent.isPresent()) {
			return optionalEvent.get();
		}
		else {
			return null;
		}
	}
	// UPDATE AN EVENT
	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}
	// DELETE AN EVENT
	@RequestMapping(value="/event/{id}", method=RequestMethod.DELETE)
	public String deleteEvent(@PathVariable("id") Long id) {
		eventRepository.deleteById(id);
		return "redirect:/home";
	}

}
