//package ru.gb.telegrambot;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//
//import javax.annotation.PostConstruct;
//
//@Slf4j
//@Configuration
//public class TelegramBotConfig {
//
////    @Autowired
//    private Bot bot;
//
//    @Autowired
//    public void setTelegramBotMain(Bot bot) {
//        this.bot = bot;
//    }
//
//
//
//    @PostConstruct
//    public void start() {
//        try {
//            log.info("Instantiate Telegram Bots API...");
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//
//            log.info("Register Telegram Bots API...");
//            botsApi.registerBot(bot);
//        } catch (TelegramApiException e) {
//            log.error("Exception instantiate Telegram Bot!", e);
//        }
//    }
//}