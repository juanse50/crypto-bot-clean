package co.com.cryptobot.telegram;

import co.com.cryptobot.model.message.Message;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import org.springframework.stereotype.Component;

@Component
public class Provider  {

    private final TelegramBot bot;
    private String token = System.getenv("BOT_TOKEN");

    public Provider() {
        this.bot = new TelegramBot(token);
    }

    public SendResponse sendMessage(Message message) {
        SendMessage sendMessage = new SendMessage(message.getChat().getId(), message.getText());
        return bot.execute(sendMessage);
    }
}

