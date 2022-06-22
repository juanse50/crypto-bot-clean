package co.com.cryptobot.jpa.Crypto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface CryptoDataRepository extends CrudRepository<CryptoData, String>, QueryByExampleExecutor<CryptoData> {

}
