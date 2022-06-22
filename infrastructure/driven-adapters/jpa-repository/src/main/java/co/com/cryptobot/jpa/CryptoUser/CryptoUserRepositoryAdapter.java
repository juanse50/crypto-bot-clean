package co.com.cryptobot.jpa.CryptoUser;


import co.com.cryptobot.jpa.helper.AdapterOperations;

import co.com.cryptobot.model.cryptouser.CryptoUser;
import co.com.cryptobot.model.cryptouser.gateways.CryptoUserRepository;
import org.reactivecommons.utils.ObjectMapper;

import java.util.List;
import java.util.Optional;

public class CryptoUserRepositoryAdapter extends AdapterOperations<CryptoUser, CryptoUserData, Integer, CryptoUserDataRepository> implements CryptoUserRepository {

    public CryptoUserRepositoryAdapter(CryptoUserDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, CryptoUser.CryptoUserBuilder.class).build());
    }



    @Override
    public CryptoUser createCryptoUser(CryptoUser cryptoUser) {
        return super.save(cryptoUser);
    }

    @Override
    public void deleteCryptoUser(Integer id) {
         repository.deleteById(id);
    }

    @Override
    public List<CryptoUser> findByUser_Id(Long id) {
        return super.repository.findByUser_Id(id);
    }

    @Override
    public Optional<CryptoUser> findByUser_IdAndCrypto_Currency(Long id, String currency) {
        return repository.findByUser_IdAndCrypto_Currency(id, currency);
    }
}
