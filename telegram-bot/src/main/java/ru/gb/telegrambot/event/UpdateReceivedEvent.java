package ru.gb.telegrambot.event;

import org.springframework.context.ApplicationEvent;

public class UpdateReceivedEvent extends ApplicationEvent {
    public UpdateReceivedEvent(Object source) {
        super(source);
    }
}
