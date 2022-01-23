package ru.gb.telegrambot;

public enum Command {
    START,
    HELP,
    SUBSCRIBE("Подписаться на получение оповещений"),
    UNSUBSCRIBE("Отписаться от получений оповещений");

    private final String description;

    Command() {
        this.description = "";
    }

    Command(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "/" + this.name();
    }
}
