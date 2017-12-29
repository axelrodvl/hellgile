package co.axelrod.hellgile.game.telegram;

import co.axelrod.hellgile.Game;
import co.axelrod.hellgile.game.telegram.interactions.start.StartGame;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 25.12.2017.
 */
public class HellgileBot extends TelegramLongPollingBot {
    private Map<Long, Game> activeGames = new HashMap<>();
    private Map<Long, Class> interactions = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Game game;
            //Project project;
            Long chatId = update.getMessage().getChatId();
            System.out.println("new request: " + chatId);

            // Create new game if it's new user
            if(!activeGames.containsKey(chatId)) {
                //activeGames.put(chatId, new Game(chatId));
            }

            game = activeGames.get(chatId);
            //project = game.getProject();

            Class interaction;

            if(interactions.get(chatId) == null) {
                interaction = StartGame.class;
                interactions.put(chatId, interaction);
            } else {
                interaction = interactions.get(chatId);
            }

            try {
                UserInteractionBuilder userInteractionBuilder;
                try {
                    Constructor<UserInteractionBuilder> constructor = interaction.getConstructor(Long.class);
                    userInteractionBuilder = constructor.newInstance(chatId);
                } catch (Exception ex) {
                    throw new RuntimeException();
                }

                for(SendMessage message : userInteractionBuilder.build(update)) {
                    execute(message);
                }

                interactions.put(chatId, userInteractionBuilder.getNextInteraction());
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "HellgileBot";
    }

    @Override
    public String getBotToken() {
        return new TokenStorageImpl().getToken();
    }
}
