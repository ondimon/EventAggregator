package ru.gb.eventservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.service.EventService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class EventFrontController {
    private final EventService eventService;

    @GetMapping({"","/"})
    public String showAddForm() {
        return "add_event";
    }

    @PostMapping({"","/"})
    public String saveEvent(@ModelAttribute EventDto eventDto) {
        eventService.saveOrUpdate(eventDto);
        return "redirect:/events";
    }
}
