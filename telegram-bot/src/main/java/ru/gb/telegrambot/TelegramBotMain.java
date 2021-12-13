package ru.gb.telegrambot;

import lombok.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gb.telegrambot.paths.PathTxtFiles;
import ru.gb.telegrambot.settings.SettingsKeyBord;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TelegramBotMain extends TelegramLongPollingBot {
    private final PathTxtFiles pathTxtFiles = new PathTxtFiles();
    private final SettingsKeyBord settingsKeyBord = new SettingsKeyBord();

    String webHookPath;
    String botUserName;
    String botToken;

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
            String chat_id = String.valueOf(update.getMessage().getChatId());

            switch (message_text) {
                case "/start":
                case "/back":
                case "Назад": {
                    startMessage(sendMessage, chat_id);
                    break;
                }
                case "/help": {

                    helpMessage(sendMessage, chat_id);
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

                    sendMessage.setChatId(chat_id);
                    sendMessage.setText(sendMessage(pathTxtFiles.subscriptionMessage()));
                    sendMessage.setText("GeekBrains");
                    sendMessage.setReplyMarkup(keyboardMarkup);

                    execute(sendMessage);

                    break;
                }
                case "Подписаться": {

                    execute(SendMessage.builder()
                            .chatId(chat_id)
                            .text("Вы подписались")
                            .build());
                    startMessage(sendMessage, chat_id);
                    break;
                }
                default:
                    execute(SendMessage.builder()
                            .chatId(chat_id)
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

    public void startMessage(SendMessage sendMessage, String chat_id) throws TelegramApiException {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        settingsKeyBord.keyBoardMarkupSettings(keyboardMarkup);

        row.add("/start ");
        row.add("/help");
        row.add("Каналы");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(chat_id);
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
