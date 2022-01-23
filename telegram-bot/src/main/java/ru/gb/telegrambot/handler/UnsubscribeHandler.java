package ru.gb.telegrambot.handler;

import lombok.RequiredArgsConstructor;
import ru.gb.telegrambot.Command;
import ru.gb.telegrambot.annotation.BotCommand;
import ru.gb.telegrambot.builder.MessageBuilder;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.service.UserService;

@BotCommand(command = {Command.UNSUBSCRIBE})
@RequiredArgsConstructor
public class UnsubscribeHandler extends AbstractHandler{
    private final UserService userService;

    @Override
    public void handle(User user, String text) {
        MessageBuilder messageBuilder = MessageBuilder.builder(user);
        if(user.isNew()) {
            messageBuilder.line("Вы не подписаны на рассылку");
            sendMessage(messageBuilder.build());
            return;
        }
        userService.delete(user);
        messageBuilder.line("Вы успешно отписаны от рассылки.");
        sendMessage(messageBuilder.build());
    }
}
