package co.com.cryptobot.jpa.Crypto;

import co.com.cryptobot.jpa.CryptoUser.CryptoUserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoData {

    @Id
    private String currency;

    @Column
    private String name;

    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL)
    private List<CryptoUserData> cryptoUsers;
}