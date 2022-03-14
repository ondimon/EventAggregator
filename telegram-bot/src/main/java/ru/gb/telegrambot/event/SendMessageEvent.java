package ru.gb.telegrambot.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@RequiredArgsConstructor
public class SendMessageEvent <T extends BotApiMethod>  {

    @Getter
    private final T source;
}
