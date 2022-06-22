package co.com.cryptobot.usecase.crypto;

import co.com.cryptobot.model.crypto.Crypto;
import co.com.cryptobot.model.crypto.gateways.CryptoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
public class CryptoUseCase {
    private final CryptoRepository repository;

    public List<Crypto> findAll(){
        return repository.findAll();
    }

    public Crypto addCrypto(Crypto crypto){
        Crypto cry=repository.createCrypto(crypto);

        return cry;
    }


}
