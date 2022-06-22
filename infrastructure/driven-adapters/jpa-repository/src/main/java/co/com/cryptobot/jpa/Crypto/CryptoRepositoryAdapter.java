package co.com.cryptobot.jpa.Crypto;

import co.com.cryptobot.jpa.helper.AdapterOperations;
import co.com.cryptobot.model.crypto.Crypto;
import co.com.cryptobot.model.crypto.gateways.CryptoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class CryptoRepositoryAdapter extends AdapterOperations<Crypto, CryptoData, String, CryptoDataRepository> implements CryptoRepository {

    public CryptoRepositoryAdapter(CryptoDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Crypto.CryptoBuilder.class).build());
    }

    @Override
    public Crypto createCrypto(Crypto crypto) {
        return super.save(crypto);
    }

    @Override
    public void deleteCrypto(String id) {
         repository.deleteById(id);
    }

    @Override
    public Optional<Crypto> findById(String id){return null;}

}
