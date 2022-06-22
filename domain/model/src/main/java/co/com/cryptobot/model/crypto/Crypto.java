package co.com.cryptobot.model.crypto;
import co.com.cryptobot.model.cryptouser.CryptoUser;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Crypto {
    private String currency;
    private String name;
    private List<CryptoUser> cryptoUserList;
}
