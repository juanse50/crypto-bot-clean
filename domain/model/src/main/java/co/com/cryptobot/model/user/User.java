package co.com.cryptobot.model.user;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
}
