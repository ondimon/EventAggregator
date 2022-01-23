package ru.gb.telegrambot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambot.event.SendMessageEvent;
import ru.gb.telegrambot.event.UpdateReceivedEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    @Getter
    private String botUsername;

    @Value("${bot.token}")
    @Getter
    private String botToken;

    private final ApplicationEventPublisher publisher;

    @Override
    public void onUpdateReceived(Update update) {
        publisher.publishEvent(new UpdateReceivedEvent(update));
    }

    @SneakyThrows(TelegramApiException.class)
    @EventListener(classes = {SendMessageEvent.class})
    public void executeSafe(SendMessageEvent event) {
        execute((SendMessage) event.getSource());
    }
}
