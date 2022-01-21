package ru.gb.telegrambot.service;


import ru.gb.telegrambot.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getEventFromDateUpdate(LocalDateTime dateUpdate);
}
