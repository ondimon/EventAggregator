package ru.gb.telegrambot.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.event.SendMessageEvent;

@RequiredArgsConstructor
public abstract class AbstractHandler {

    @Autowired
    private ApplicationEventPublisher publisher;

    public final void sendMessage(SendMessage message) {
        publisher.publishEvent(new SendMessageEvent<>(message));
    }

    public abstract void handle(User user, String text);
}
