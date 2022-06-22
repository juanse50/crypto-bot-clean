package co.com.cryptobot.pubsub;

import co.com.cryptobot.model.pubsubmessage.gateways.PubSubMessageRepository;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class PubSubClient  implements PubSubMessageRepository {
    private String projectID;
    private String newsTopicID;
    private TopicName newsTopicName;
    private String exchangeTopicID;
    private TopicName exchangeTopicName;

    public PubSubClient() {
        //this.projectID = System.getenv("PROJECT_ID");
        // this.newsTopicID = System.getenv("NEWS_TOPIC_ID");
        this.projectID="cryptobot-345516";
        this.newsTopicID="messaging-topic-news";
        this.exchangeTopicID="messaging-topic-exchange";
        this.newsTopicName = TopicName.of(this.projectID, this.newsTopicID);
        //this.exchangeTopicID = System.getenv("EXCHANGE_TOPIC_ID");
        this.exchangeTopicName = TopicName.of(this.projectID, this.exchangeTopicID);
    }

    @Override
    public void publishMessage(String message, String topic) throws IOException, InterruptedException {
        Publisher publisher = null;
        try {
            if (topic == "news") {
                publisher = Publisher.newBuilder(newsTopicName).build();
            } else {
                publisher = Publisher.newBuilder(exchangeTopicName).build();
            }

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            ApiFuture<String> future = publisher.publish(pubsubMessage);

            ApiFutures.addCallback(future, new ApiFutureCallback<String>() {

                        @Override
                        public void onFailure(Throwable throwable) {
                            if (throwable instanceof ApiException) {
                                ApiException apiException = ((ApiException) throwable);
                                System.out.println(apiException.getStatusCode().getCode());
                                System.out.println(apiException.isRetryable());
                                throw apiException;
                            }
                            System.out.println("Error publishing message : " + message);
                        }

                        @Override
                        public void onSuccess(String messageId) {
                            System.out.println("Published message ID: " + messageId);
                        }
                    },
                    MoreExecutors.directExecutor());
        } finally {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    // Create an inbound channel adapter to listen to the subscription `sub-one` and send
// messages to the input message channel.
    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(
            @Qualifier("inputMessageChannel") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "messaging-topic-news-sub");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(String.class);
        return adapter;
    }

    // Define what happens to the messages arriving in the message channel.
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(
            String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        System.out.println("Message arrived via an inbound channel adapter from sub-one! Payload: " + payload);
        message.ack();
    }
}