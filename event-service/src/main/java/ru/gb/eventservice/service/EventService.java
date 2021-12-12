package ru.gb.eventservice.service;

import ru.gb.eventservice.domain.Event;

import java.util.List;

public interface EventService {
    Event findById(Long id);
    Event saveOrUpdate(Event event);
    List<Event> getAll();
}
