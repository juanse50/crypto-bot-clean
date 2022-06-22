package co.com.cryptobot.model.cryptouser.gateways;

import co.com.cryptobot.model.cryptouser.CryptoUser;

import java.util.List;
import java.util.Optional;

public interface CryptoUserRepository {


    List<CryptoUser> findAll();
    CryptoUser createCryptoUser(CryptoUser cryptoUser);
    void deleteCryptoUser(Integer id);

    List<CryptoUser> findByUser_Id(Long id);

    Optional<CryptoUser> findByUser_IdAndCrypto_Currency(Long id, String currency);


}
