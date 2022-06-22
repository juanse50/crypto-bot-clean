package co.com.cryptobot.model.pubsubmessage;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder(toBuilder = true)
public class PubSubMessage {
    private String data;
    private String messageId;
}
