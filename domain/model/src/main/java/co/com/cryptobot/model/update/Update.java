package co.com.cryptobot.model.update;
import co.com.cryptobot.model.message.Message;
import lombok.*;


@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class Update {
    private Integer update_id;
    private Message message;
}
