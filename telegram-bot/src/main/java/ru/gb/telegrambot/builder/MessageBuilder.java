package ru.gb.telegrambot.builder;



import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.gb.telegrambot.Command;
import ru.gb.telegrambot.domain.User;

import java.util.ArrayList;
import java.util.List;

public final class MessageBuilder {
    @Setter
    private String chatId;
    private final StringBuilder sb = new StringBuilder();
    private final List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    private List<InlineKeyboardButton> row = null;

    private MessageBuilder() {
    }

    public static MessageBuilder builder(String chatId) {
        MessageBuilder builder = new MessageBuilder();
        builder.setChatId(chatId);
        return builder;
    }


    public static MessageBuilder builder(User user) {
        return builder(String.valueOf(user.getTelegramId()));
    }

    public MessageBuilder line(String text, Object... args) {
        sb.append(String.format(text, args));
        return line();
    }

    public MessageBuilder line() {
        sb.append(String.format("%n"));
        return this;
    }

    public MessageBuilder row() {
        addRowToKeyboard();
        row = new ArrayList<>();
        return this;
    }

    public MessageBuilder button(String text, String callbackData) {
        var button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        row.add(button);
        return this;
    }

    public MessageBuilder button(String text, Command command) {
        return button(text, command.toString());
    }


    public MessageBuilder buttonWithArguments(String text, Command command) {
        return button(text, command.toString() + " " + text);
    }

    public SendMessage build() {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(sb.toString().replace("_", " "));

        addRowToKeyboard();

        if (!keyboard.isEmpty()) {
            var markup = new InlineKeyboardMarkup();
            markup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(markup);
        }

        return sendMessage;
    }

    private void addRowToKeyboard() {
        if (row != null) {
            keyboard.add(row);
        }
    }
}
