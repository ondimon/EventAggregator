package ru.gb.telegrambot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.service.UserService;
import ru.gb.telegrambot.settings.SettingsKeyBord;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot implements Serializable {

    @Autowired
    private UserService userService;

    private final SettingsKeyBord settingsKeyBord = new SettingsKeyBord();

    @Value("${file.startMessage}")
    private String startMessage;

    @Value("${file.helpMessage}")
    private String helpMessage;

    @Value("${file.subscriptionMessage}")
    private String subscriptionMessage;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
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

                    resourcesChannels(sendMessage, chatId);

                    break;
                }
                case "Подписаться": {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd")
                            .format(Calendar.getInstance().getTime());
                    User user = new User();

                    user.setTelegramId(chatId);
                    user.setDateRegistration(timeStamp);
                    user.setUserName( update.getMessage().getChat().getFirstName());


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

    private void resourcesChannels(SendMessage sendMessage, Long chatId) throws TelegramApiException {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

        row.add("Подписаться");
        row.add("Назад");

        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(sendMessage(new File(subscriptionMessage)));
        sendMessage.setText("GeekBrains");
        sendMessage.setReplyMarkup(keyboardMarkup);

        execute(sendMessage);
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
        sendMessage.setText(sendMessage(new File(helpMessage)));
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
        sendMessage.setText(sendMessage(new File(startMessage)));
        sendMessage.setReplyMarkup(keyboardMarkup);
        execute(sendMessage);
    }

    @SneakyThrows
    public String sendMessage(File fileMessage) {
        long line;
        FileReader fileReader = new FileReader(fileMessage);
        StringBuilder sb = new StringBuilder();
        while ((line = fileReader.read()) != -1) {
            sb.append((char) line);
        }
        fileReader.close();
        return String.valueOf(sb);
    }


}
