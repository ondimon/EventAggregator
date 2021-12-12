package ru.gb.eventservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.service.EventServiceImpl;
import java.util.List;

/**
 * Контроллер пользовательского графического интерфейса (GUI)
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UIController {

    /**
     * Сервис событий
     *
     * @see Event
     */
    private final EventServiceImpl eventService;

    /**
     * Возвращает GUI список всех событий из базы данных
     *
     * @return Список событий
     */
    @GetMapping(value = "/events")
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }
}
