package co.com.cryptobot.telegram;

import co.com.cryptobot.model.message.Message;
import co.com.cryptobot.model.update.Update;

public class MessagingService {
    private Provider provider;

    public MessagingService(Provider provider) {
        this.provider = provider;
    }

    public void pushMessageToUser(Message message) {
        this.provider.sendMessage(message);
    }

    public void pushUnrecognizedCommand(Update update) {
        String unrecognizedCommandMessage = "Comando desconocido. ¿Qué dijiste?";
        update.getMessage().setText(unrecognizedCommandMessage);
        this.provider.sendMessage(update.getMessage());
    }
}
