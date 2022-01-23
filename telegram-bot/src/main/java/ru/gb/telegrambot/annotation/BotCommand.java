package ru.gb.telegrambot.annotation;

import org.springframework.stereotype.Component;
import ru.gb.telegrambot.Command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BotCommand {
    Command[] command();
}
