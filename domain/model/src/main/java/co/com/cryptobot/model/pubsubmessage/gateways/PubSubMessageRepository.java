package co.com.cryptobot.model.pubsubmessage.gateways;

import java.io.IOException;

public interface PubSubMessageRepository {
    public void publishMessage(String message, String topic) throws IOException, InterruptedException;
}
