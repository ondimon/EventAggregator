package ru.gb.parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.parser.client.EventServiceClient;
import ru.gb.parser.domain.Event;

@Service
public class EventServiceImp implements EventService{

    private EventServiceClient eventServiceClient;

    @Autowired
    public void setEventServiceClient(EventServiceClient eventServiceClient) {
        this.eventServiceClient = eventServiceClient;
    }

    @Override
    public void saveEvent(Event event) {
        eventServiceClient.saveEvent(event);
    }
}
