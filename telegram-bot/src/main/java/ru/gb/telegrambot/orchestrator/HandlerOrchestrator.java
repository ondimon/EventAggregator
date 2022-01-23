package ru.gb.telegrambot.orchestrator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.telegrambot.annotation.BotCommand;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.handler.AbstractHandler;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class HandlerOrchestrator {
    private final List<AbstractHandler> handlers;


    public void operate(User user, String text) {
        AbstractHandler handler = getHandler(text);
        handler.handle(user, text);
    }

    private AbstractHandler getHandler(String text) {
        return handlers.stream()
                .filter(h -> h.getClass()
                        .isAnnotationPresent(BotCommand.class))
                .filter(h -> Stream.of(h.getClass()
                                .getAnnotation(BotCommand.class)
                                .command())
                        .anyMatch(c -> c.toString().equalsIgnoreCase(extractCommand(text))))
                .findAny()
                .orElseGet(()-> getHandler("/START"));
    }

    private  String extractCommand(String text) {
        return text.split(" ")[0];
    }
}
