package co.com.cryptobot.api.cryptoApi;

import co.com.cryptobot.model.crypto.Crypto;
import co.com.cryptobot.usecase.crypto.CryptoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CryptoApi {
    private final CryptoUseCase useCase;


    @PostMapping(path = "/crearCrypto")
    public Crypto addCrypto(@RequestBody Crypto crypto) {

        return useCase.addCrypto(crypto);
    }

    @GetMapping(path = "cryptos")
    public List<Crypto> getAll(){
        return useCase.findAll();
    }
}

