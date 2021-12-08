package ru.gb.eventservice.service;

import ru.gb.eventservice.domain.Event;

public interface EventService {
    Event findById(Long id);
    Event saveOrUpdate(Event event);
}
