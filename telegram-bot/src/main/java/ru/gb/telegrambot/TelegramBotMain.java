package ru.gb.telegrambot;

import lombok.*;
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

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@Component
@NoArgsConstructor
public class TelegramBotMain extends TelegramLongPollingBot {
    private UserService userService = new UserService();
    private final PathTxtFiles pathTxtFiles = new PathTxtFiles();
    private final SettingsKeyBord settingsKeyBord = new SettingsKeyBord();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return "@Geek_Brainsbot";
    }


    @Override
    public String getBotToken() {
        return "5023935402:AAEzmYqCDp0xHPvfGRjIUx2rHeUOBFvqfIM";
    }

    @SneakyThrows
    @PostConstruct
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
                    user.setTelegram_id(chatId);
                    user.setId(1L);

                     userService.saveUser(user);


                    startMessage(sendMessage, chatId);
                    break;
                }
                default:
                    execute(SendMessage.builder()
                            .chatId(String.valueOf(chatId))
                            .text(update.getMessage().getText())
                            .build());

                    break;
            }


        }
    }


    private void helpMessage(SendMessage sendMessage, String chat_id) throws TelegramApiException {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

        row.add("/start");
        row.add("/back");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(chat_id);
        sendMessage.setText(sendMessage(pathTxtFiles.helpMessage()));
        sendMessage.setReplyMarkup(keyboardMarkup);
        execute(sendMessage);
    }

    public void startMessage(SendMessage sendMessage, Long chat_id) throws TelegramApiException {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

        row.add("/start ");
        row.add("/help");
        row.add("Каналы");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(String.valueOf(chat_id));
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
