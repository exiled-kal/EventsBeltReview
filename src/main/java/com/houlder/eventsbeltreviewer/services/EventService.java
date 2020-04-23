package com.houlder.eventsbeltreviewer.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.houlder.eventsbeltreviewer.models.Event;
import com.houlder.eventsbeltreviewer.repositories.EventRepository;

@Service
public class EventService {
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public void createEvent(@Valid Event event) {
		eventRepository.save(event);
	}
	

}
