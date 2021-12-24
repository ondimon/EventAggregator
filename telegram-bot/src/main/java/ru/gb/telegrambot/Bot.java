package ru.gb.telegrambot;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.paths.PathTxtFiles;
import ru.gb.telegrambot.service.UserService;
import ru.gb.telegrambot.settings.SettingsKeyBord;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot  implements Serializable {

    @Autowired
    private UserService userService;

    private final PathTxtFiles pathTxtFiles = new PathTxtFiles();
    private final SettingsKeyBord settingsKeyBord = new SettingsKeyBord();

    @Override
    public String getBotUsername() {
        return "@Geek_Brainsbot";
    }


    @Override
    public String getBotToken() {
        return "5023935402:AAEzmYqCDp0xHPvfGRjIUx2rHeUOBFvqfIM";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage sendMessage = new SendMessage();

            String message_text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (message_text) {
                case "/start":
                case "/back":
                case "Назад": {
                    startMessage(sendMessage, chatId);
                    break;
                }
                case "/help": {

                    helpMessage(sendMessage, String.valueOf(chatId));
                    break;
                }
                case "Каналы": {

                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    KeyboardRow row = new KeyboardRow();

                    settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

                    row.add("Подписаться");
                    row.add("Назад");

                    keyboard.add(row);
                    keyboardMarkup.setKeyboard(keyboard);

                    sendMessage.setChatId(String.valueOf(chatId));
                    sendMessage.setText(sendMessage(pathTxtFiles.subscriptionMessage()));
                    sendMessage.setText("GeekBrains");
                    sendMessage.setReplyMarkup(keyboardMarkup);

                    execute(sendMessage);

                    break;
                }
                case "Подписаться": {
                    User user = new User();
                    user.setTelegramId(chatId);

                    userService.saveUser(user);


                    startMessage(sendMessage, chatId);
                    break;
                }
                default:

                    execute(new SendMessage().setChatId(update.getMessage().getChatId())
                            .setText("Hi!"));


                    break;
            }


        }
    }


    private void helpMessage(SendMessage sendMessage, String chatId) throws TelegramApiException {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

        row.add("/start");
        row.add("/back");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(chatId);
        sendMessage.setText(sendMessage(pathTxtFiles.helpMessage()));
        sendMessage.setReplyMarkup(keyboardMarkup);
        execute(sendMessage);
    }

    public void startMessage(SendMessage sendMessage, Long chatId) throws TelegramApiException {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

        row.add("/start ");
        row.add("/help");
        row.add("Каналы");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(sendMessage(pathTxtFiles.startMessage()));
        sendMessage.setReplyMarkup(keyboardMarkup);
        execute(sendMessage);
    }

    @SneakyThrows
    public @NonNull String sendMessage(File fileMessage) {
        int line;
        FileReader fileReader = new FileReader(fileMessage);
        StringBuilder sb = new StringBuilder();
        while ((line = fileReader.read()) != -1) {
            sb.append((char) line);
        }
        fileReader.close();
        return String.valueOf(sb);
    }


}
