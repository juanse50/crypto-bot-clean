package co.com.cryptobot.model.chat;
import lombok.*;

@Data
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Chat {
    private Long id;
    private String type;

    public Chat(Long id) {
        this.id = id;
    }
}
