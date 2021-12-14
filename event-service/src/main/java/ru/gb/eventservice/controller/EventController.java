package ru.gb.eventservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.gb.eventservice.controller.filter.EventFilter;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.dto.Response;
import ru.gb.eventservice.dto.ResponseAddNewEvent;
import ru.gb.eventservice.exception.EventNotFoundException;
import ru.gb.eventservice.service.EventService;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/events")
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
    @PostMapping({"","/"})
    public ResponseEntity<ResponseAddNewEvent> addNewEvent(@RequestBody @Valid EventDto eventDto){
        Event newEvent = eventService.saveOrUpdate(eventDto);
        ResponseAddNewEvent responseAddNewEvent = new ResponseAddNewEvent();
        responseAddNewEvent.setId(newEvent.getId());
        responseAddNewEvent.setResult(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAddNewEvent);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Response> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Response error = new Response();
        error.setResult(false);
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            sb.append(String.format("field %s : %s", fieldError.getField(), fieldError.getDefaultMessage()));
            sb.append(";");
        }
        error.setError(sb.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
