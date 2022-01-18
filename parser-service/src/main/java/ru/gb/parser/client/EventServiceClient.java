package ru.gb.parser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.gb.parser.domain.Event;

@FeignClient(name = "EventServiceClient", url = "localhost:8081")
public interface EventServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveEvent(@RequestBody Event event);
}
