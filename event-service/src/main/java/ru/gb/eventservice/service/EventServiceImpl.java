package ru.gb.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.domain.Tag;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.exception.EventNotFoundException;
import ru.gb.eventservice.mapper.EventMapper;
import ru.gb.eventservice.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("EventService")
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;
    private EventMapper eventMapper;
    protected TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<EventDto> findAllDto(Specification<Event> specification) {
        List<EventDto> result = new ArrayList<>();
        List<Event> events = eventRepository.findAll(specification);
        events.forEach(event -> result.add(eventMapper.fromEvent(event)));
        return result;
    }

    @Override
    public EventDto findByIdDto(Long id) throws EventNotFoundException {
        Event event = findById(id);
        return eventMapper.fromEvent(event);
    }

    @Override
    public Event findById(Long id)  throws EventNotFoundException {
        String notFoundText = String.format("Can't found event with id = %d", id);
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(notFoundText));
    }

    @Override
    public Event saveOrUpdate(Event event) {
        event.setDateUpdate(LocalDateTime.now());
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event saveOrUpdate(EventDto eventDto) {
        Event event = eventMapper.fromEventDto(eventDto);
        List<Tag> tags = event.getTags();
        if (tags != null) {
            for (Tag tag: tags) {
                if(tag.getId() != null) {
                    continue;
                }
                tag.setId(tagService.saveOrUpdate(tag).getId());
            }
        }
        return saveOrUpdate(event);
    }

}
