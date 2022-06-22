package co.com.cryptobot.jpa.CryptoUser;

import co.com.cryptobot.jpa.Crypto.CryptoData;
import co.com.cryptobot.model.cryptouser.CryptoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface CryptoUserDataRepository extends CrudRepository<CryptoUserData, Integer> , QueryByExampleExecutor<CryptoUserData> {
    Optional<CryptoUser> findByUser_IdAndCrypto_Currency(Long id, String currency);
    List<CryptoUser> findByUser_Id(Long id);
}
