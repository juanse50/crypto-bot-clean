package co.com.cryptobot.config;

import co.com.cryptobot.model.crypto.gateways.CryptoRepository;
import co.com.cryptobot.model.pubsubmessage.gateways.PubSubMessageRepository;
import co.com.cryptobot.usecase.crypto.CryptoUseCase;
import co.com.cryptobot.usecase.pubsub.PubsubUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
public class UseCasesConfig {

        @Bean
        public PubsubUseCase getPubsubUseCase(PubSubMessageRepository pubSubMessageRepository){
                return new PubsubUseCase(pubSubMessageRepository);
        }

        @Bean
        public CryptoUseCase getCryptoUseCase(CryptoRepository cryptoRepository){
                return new CryptoUseCase(cryptoRepository);
        }
}
