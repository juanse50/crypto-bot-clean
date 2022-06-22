package co.com.cryptobot.model.crypto.gateways;

import co.com.cryptobot.model.crypto.Crypto;

import java.util.List;
import java.util.Optional;

public interface CryptoRepository {
    List<Crypto> findAll();


    Crypto createCrypto(Crypto crypto);
    void deleteCrypto(String id);

    Optional<Crypto> findById(String crypto);
}
