package com.example;

import com.example.commands.AppBotCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private AppBotCommand botCommand;

    @Override
    public String getBotUsername() {
        return "TestBot_005";
    }

    @Override
    public String getBotToken() {
        return "6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            String response = botCommand.processCommand(messageText);

            SendMessage message = new SendMessage(chatId, response);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
