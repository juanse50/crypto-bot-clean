package co.com.cryptobot.api.pubsubApi;

import co.com.cryptobot.usecase.pubsub.PubsubUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiPubsub {

   private final PubsubUseCase useCase;


    @PostMapping(path = "/publish")
    public ResponseEntity<String> publish(@RequestParam String message) {
//      return useCase.doAction();
        try {
            this.useCase.publishMessage(message, "news");
            return new ResponseEntity<>("Mensaje enviado", HttpStatus.CREATED);
        }catch (Exception e){return new ResponseEntity<>("Error en el envio del mensaje", HttpStatus.CONFLICT);}

    }
}
