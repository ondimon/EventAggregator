package ru.gb.telegrambot;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gb.telegrambot.domain.User;
import ru.gb.telegrambot.event.UpdateReceivedEvent;
import ru.gb.telegrambot.orchestrator.HandlerOrchestrator;
import ru.gb.telegrambot.service.UserService;

@RequiredArgsConstructor
@Component
public class UpdateReceivedProcessor {
    private final HandlerOrchestrator orchestrator;
    private final UserService userService;

    @EventListener(classes = {UpdateReceivedEvent.class})
    public void handleUpdate(UpdateReceivedEvent updateCreationEvent) {
        Update update = (Update) updateCreationEvent.getSource();
        Message message = update.getMessage();
        long userID = 0;
        String text = null;
        String userName = "";
        if(isMessageWithText(update)) {
            userID = message.getFrom().getId();
            text = message.getText();
            userName = message.getFrom().getFirstName();
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            userID = callbackQuery.getMessage().getChatId();
            text = callbackQuery.getData();
            userName = callbackQuery.getMessage().getChat().getFirstName();
        }
        if(userID != 0 && text != null) {

            User user = userService.findByChatId(userID).orElseGet(User::new);
            if(user.isNew()) {
                user.setTelegramId(userID);
                user.setUserName(userName);
            }
            orchestrator.operate(user, text);
        }
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }
}
