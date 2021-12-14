package ru.gb.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.exception.EventNotFoundException;
import ru.gb.eventservice.mapper.EventMapper;
import ru.gb.eventservice.repository.EventRepository;
import java.util.ArrayList;
import java.util.List;

@Service("EventService")
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<EventDto> findAllDto(Specification<Event> specification) {
        List<EventDto> result = new ArrayList<>();
        List<Event> events = eventRepository.findAll(specification);
        events.forEach(event -> result.add(EventMapper.MAPPER.fromEvent(event)));
        return result;
    }

    @Override
    public EventDto findByIdDto(Long id) throws EventNotFoundException {
        Event event = findById(id);
        return EventMapper.MAPPER.fromEvent(event);
    }

    @Override
    public Event findById(Long id)  throws EventNotFoundException {
        String notFoundText = String.format("Can't found event with id = %d", id);
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(notFoundText));
    }

    @Override
    public Event saveOrUpdate(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event saveOrUpdate(EventDto eventDto) {
        return saveOrUpdate(EventMapper.MAPPER.fromEventDto(eventDto));
    }

}
