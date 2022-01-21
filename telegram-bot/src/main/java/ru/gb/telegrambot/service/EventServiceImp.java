package ru.gb.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.telegrambot.client.EventServiceClient;
import ru.gb.telegrambot.domain.Event;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class EventServiceImp implements EventService{

    private EventServiceClient eventServiceClient;

    @Autowired
    public void setEventServiceClient(EventServiceClient eventServiceClient) {
        this.eventServiceClient = eventServiceClient;
    }

    @Override
    public List<Event> getEventFromDateUpdate(LocalDateTime dateUpdate) {
        return eventServiceClient.getEventFromDateUpdate(dateUpdate.toString());
    }
}
