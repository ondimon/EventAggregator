package ru.gb.eventservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.eventservice.controller.filter.EventFilter;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.exception.EventNotFoundException;
import ru.gb.eventservice.service.EventService;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class EventController {
    private EventService eventService;

    @Autowired
    void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<EventDto>> index(@RequestParam Map<String, String> requestParams){
        EventFilter eventFilter = new EventFilter(requestParams);
        return ResponseEntity.ok().body(eventService.findAllDto(eventFilter.getSpec()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long id) throws EventNotFoundException {
        EventDto event = eventService.findByIdDto(id);
        return ResponseEntity.ok().body(event);
    }
}
