package co.com.cryptobot.telegram;

import co.com.cryptobot.model.chat.Chat;
import co.com.cryptobot.model.crypto.Crypto;
import co.com.cryptobot.model.crypto.gateways.CryptoRepository;
import co.com.cryptobot.model.cryptouser.CryptoUser;
import co.com.cryptobot.model.cryptouser.gateways.CryptoUserRepository;
import co.com.cryptobot.model.message.Message;
import co.com.cryptobot.model.update.Update;
import co.com.cryptobot.model.user.User;
import co.com.cryptobot.pubsub.PubSubClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ExchangeService {
    private final String trackingSuccessfullyConfiguredMessage = "Confirmado! te estaré informando sobre el precio de ";
    private final String deleteTrackingSuggestionMessage = "Si quieres dejar de darle seguimiento a la moneda, ejecuta nuevamente el comando";
    private final String deleteTrackOfACryptoMessage = "¡confirmado! ya no te brindaré seguimiento de la moneda ";
    private final String notFollowingAnyCryptoMessage = "No estas haciendo seguimiento a ninguna criptomoneda. Selecciona una de tu interés y te mantendré al día!";

    private Provider provider;
    private CryptoUserRepository cryptoUserRepository;
    private CryptoRepository cryptoRepository;
    private PubSubClient pubSubClient;

    public ExchangeService(Provider provider, CryptoUserRepository cryptoUserRepository, CryptoRepository cryptoRepository,
                           PubSubClient pubSubClient) {
        this.provider = provider;
        this.cryptoUserRepository = cryptoUserRepository;
        this.cryptoRepository = cryptoRepository;
        this.pubSubClient = pubSubClient;
    }


    public void getCryptoValue(Update update, String crypto, String currency, int nDaysAgo) throws IOException, InterruptedException {
        JSONArray ja = new JSONArray();
        ja.add(crypto);

        JSONObject obj = new JSONObject();
        obj.put("chat_id", update.getMessage().getFrom().getId());
        obj.put("cryptos", ja);
        obj.put("rango", nDaysAgo);
        String message = obj.toString();

        this.pubSubClient.publishMessage(message, "exchange");
    }


    public void getMyCrypto(User user, String toCurrency) throws IOException, InterruptedException {
        List<CryptoUser> currencies = this.cryptoUserRepository.findByUser_Id(user.getId());
        if (currencies.size() == 0) {
            this.sendMessageToUser(user.getId(), notFollowingAnyCryptoMessage);
            return;
        }

        JSONArray ja = new JSONArray();
        for (CryptoUser cu : currencies) {
            ja.add(cu.getCrypto().getName());
        }

        JSONObject obj = new JSONObject();
        obj.put("chat_id", user.getId());
        obj.put("cryptos", ja);
        obj.put("rango", 0);
        String message = obj.toString();

        this.pubSubClient.publishMessage(message, "exchange");
    }


    public void registerCrypto(User user, String crypto) {
        Optional<CryptoUser> optionalCryptoUser = this.cryptoUserRepository.findByUser_IdAndCrypto_Currency(user.getId(), crypto);
        if (!optionalCryptoUser.isEmpty()) {
            this.cryptoUserRepository.deleteCryptoUser(optionalCryptoUser.get().getId());

            this.sendMessageToUser(user.getId(), deleteTrackOfACryptoMessage + crypto);

            return;
        }

        Optional<Crypto> optionalCrypto = this.cryptoRepository.findById(crypto);
        if (optionalCrypto.isEmpty()) {
            //TODO
            return;
        }
        Crypto cryptoCurrency = optionalCrypto.get();

        CryptoUser c = CryptoUser.builder().crypto(cryptoCurrency).user(user).build();
        this.cryptoUserRepository.createCryptoUser(c);

        this.sendMessageToUser(user.getId(), trackingSuccessfullyConfiguredMessage + cryptoCurrency.getName());
        this.sendMessageToUser(user.getId(), deleteTrackingSuggestionMessage);
    }

    private void sendMessageToUser(Long userID, String message) {
        Message telegramMessage = Message.builder().text(message).chat(new Chat(userID)).build();
        this.provider.sendMessage(telegramMessage);
    }
}
