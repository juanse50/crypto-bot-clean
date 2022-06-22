package co.com.cryptobot.model.message;
import co.com.cryptobot.model.chat.Chat;
import co.com.cryptobot.model.user.User;
import lombok.*;


@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Message {
    private Integer message_id;
    private Integer date;
    private Chat chat;
    private User from;
    private String text;

    public Message(Long chatId, String text) {
        this.chat = new Chat(chatId);
        this.text = text;
    }

}
