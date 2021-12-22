package ru.gb.telegrambot;

import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {

        TelegramBotConfig botConfig = new TelegramBotConfig();
        botConfig.start();

    }
}
