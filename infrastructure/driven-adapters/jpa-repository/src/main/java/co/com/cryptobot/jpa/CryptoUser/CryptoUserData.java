package co.com.cryptobot.jpa.CryptoUser;

import co.com.cryptobot.jpa.Crypto.CryptoData;
import co.com.cryptobot.jpa.user.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoUserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    @ManyToOne
    @JoinColumn(name = "crypto_currency")
    private CryptoData crypto;

    public CryptoUserData(UserData user, CryptoData crypto) {
        this.user = user;
        this.crypto = crypto;
    }
}
