package ru.gb.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.exception.EventNotFoundException;
import ru.gb.eventservice.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event findById(Long id) {
        String notFoundText = String.format("Can't found event with id = %d", id);
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(notFoundText));
    }

    @Override
    public Event saveOrUpdate(Event event) {
        return null;
    }
}
