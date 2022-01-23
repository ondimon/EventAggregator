package ru.gb.telegrambot.handler;

import lombok.RequiredArgsConstructor;
import ru.gb.telegrambot.Command;
import ru.gb.telegrambot.annotation.BotCommand;
import ru.gb.telegrambot.builder.MessageBuilder;
import ru.gb.telegrambot.domain.User;

@BotCommand(command = {Command.HELP, Command.START})
@RequiredArgsConstructor
public class HelpHandler extends AbstractHandler {

    @Override
    public void handle(User user, String text) {
        MessageBuilder messageBuilder = MessageBuilder.builder(user);
        messageBuilder.line("Приветcтвую тебя %s", user.getUserName())
                      .line("Я бот который собирает и присылает информацию о новых вебинарах и статьях.")
                      .line("С таких сайтов как gb.ru, skillbox.ru, skillfactory.ru");
        messageBuilder.row();
        messageBuilder.button("Помощь", Command.HELP);

        if(user.isNew()) {
            messageBuilder.line("**Если тебе интересно получать информацию, то подписывайся**");
            messageBuilder.button("Подписаться", Command.SUBSCRIBE);
        }else{
            messageBuilder.line("**Вы уже подписаны на рассылку**");
            messageBuilder.button("Отписаться", Command.UNSUBSCRIBE);
        }
        sendMessage(messageBuilder.build());
    }
}
