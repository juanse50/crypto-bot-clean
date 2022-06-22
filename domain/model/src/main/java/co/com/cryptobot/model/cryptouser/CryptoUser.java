package co.com.cryptobot.model.cryptouser;
import co.com.cryptobot.model.crypto.Crypto;
import co.com.cryptobot.model.user.User;
import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CryptoUser {

    private Integer id;
    private User user;
    private Crypto crypto;
}
