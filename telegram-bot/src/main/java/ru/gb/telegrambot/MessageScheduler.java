package ru.gb.telegrambot;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.telegrambot.builder.MessageBuilder;
import ru.gb.telegrambot.domain.Event;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.event.SendMessageEvent;
import ru.gb.telegrambot.service.EventService;
import ru.gb.telegrambot.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageScheduler {
    private final UserService userService;
    private final EventService eventService;
    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedDelay = 10000)
    public void sendNotification() {
        List<User> users =  userService.findAll();

        for (User user : users) {
            List<Event> events = eventService.getEventFromDateUpdate(user.getDateLastNotification());
            for (Event event: events) {
                MessageBuilder messageBuilder = MessageBuilder.builder(user);
                messageBuilder.line("[%s](%s)", event.getName(), event.getLink());
                publisher.publishEvent((new SendMessageEvent(messageBuilder.build())));
            }
            if(!events.isEmpty()) {
                user.setDateLastNotification(LocalDateTime.now());
                userService.saveUser(user);
            }
        }
    }
}
