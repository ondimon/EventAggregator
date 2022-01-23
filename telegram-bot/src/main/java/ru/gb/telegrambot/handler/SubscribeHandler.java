package ru.gb.telegrambot.handler;

import lombok.RequiredArgsConstructor;
import ru.gb.telegrambot.Command;
import ru.gb.telegrambot.annotation.BotCommand;
import ru.gb.telegrambot.builder.MessageBuilder;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.service.UserService;

import java.time.LocalDateTime;

@BotCommand(command = {Command.SUBSCRIBE})
@RequiredArgsConstructor
public class SubscribeHandler extends AbstractHandler{
    private final UserService userService;

    @Override
    public void handle(User user, String text) {
        MessageBuilder messageBuilder = MessageBuilder.builder(user);
        if(!user.isNew()) {
            messageBuilder.line("Вы уже подписаны на рассылку");
            sendMessage(messageBuilder.build());
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        user.setDateLastNotification(now);
        user.setDateRegistration(now);

        userService.saveUser(user);
        messageBuilder.line("Вы подписаны на рассылку.");
        sendMessage(messageBuilder.build());
    }
}
