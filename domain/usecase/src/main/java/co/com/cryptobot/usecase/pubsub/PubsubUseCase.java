package co.com.cryptobot.usecase.pubsub;

import co.com.cryptobot.model.pubsubmessage.gateways.PubSubMessageRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class PubsubUseCase {

    private final PubSubMessageRepository service;

    public void publishMessage(String message, String topic){
        try {
            service.publishMessage(message, topic);
        }catch (Exception e){
            System.out.println("error en el envio del mensaje: " + e);
        }

    }

}
