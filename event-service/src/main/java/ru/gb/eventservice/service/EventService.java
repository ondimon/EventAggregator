package ru.gb.eventservice.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.exception.EventNotFoundException;

import java.util.List;

@Service
public interface EventService {
    List<Event> findAll();
    List<EventDto> findAllDto(Specification<Event> specification);
    EventDto findByIdDto(Long id) throws EventNotFoundException;
    Event findById(Long id) throws EventNotFoundException;
    Event saveOrUpdate(Event event);
    Event saveOrUpdate(EventDto eventDto);
}
