package co.com.cryptobot.model.message.gateways;

import co.com.cryptobot.model.message.Message;
import co.com.cryptobot.model.update.Update;

public interface MessageRepository {
    void pushMessageToUser(Message message);
    void pushUnrecognizedCommand(Update update);
}
