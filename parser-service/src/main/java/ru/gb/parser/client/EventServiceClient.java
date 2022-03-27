package ru.gb.parser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.parser.domain.Event;

@FeignClient(name = "event-service")
public interface EventServiceClient {
    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveEvent(@RequestBody Event event);
}
