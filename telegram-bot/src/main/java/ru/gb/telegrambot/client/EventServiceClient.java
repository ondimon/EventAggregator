package ru.gb.telegrambot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.telegrambot.domain.Event;

import java.util.List;


@FeignClient(name = "event-service")
public interface EventServiceClient {
    @GetMapping(value = "/events?fromdateupdate={dateUpdate}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Event> getEventFromDateUpdate(@PathVariable("dateUpdate")  String dateUpdate);
}
