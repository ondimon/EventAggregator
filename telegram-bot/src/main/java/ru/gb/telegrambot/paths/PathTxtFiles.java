package ru.gb.telegrambot.paths;

import java.io.File;

public class PathTxtFiles {
    public File startMessage() {
        return new File("telegram-bot\\src\\main\\resources\\messages\\start\\startMessage.txt");
    }
    public File helpMessage() {
        return new File("telegram-bot\\src\\main\\resources\\messages\\help\\helpMessage.txt");
    }
    public File subscriptionMessage() {
        return new File("telegram-bot\\src\\main\\resources\\messages\\subscription\\subscriptionMessage.txt");
    }
}
