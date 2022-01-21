package ru.gb.telegrambot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.gb.telegrambot.domain.Event;

import java.time.LocalDateTime;
import java.util.List;


@FeignClient(name = "EventServiceClient", url = "localhost:8081")
public interface EventServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/events?fromdateupdate={dateUpdate}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Event> getEventFromDateUpdate(@PathVariable("dateUpdate")  String dateUpdate);
}
