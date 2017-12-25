package co.axelrod.hellgile.game.telegram;

import co.axelrod.hellgile.Game;
import co.axelrod.hellgile.management.Project;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 25.12.2017.
 */
public class HellgileBot extends TelegramLongPollingBot {
    private Map<Long, Game> activeGames = new HashMap<>();
    private Map<Long, UserInteractionBuilder> interactions = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Game game;
            Project project;
            Long chatId = update.getMessage().getChatId();

            // Create new game if it's new user
            if(!activeGames.containsKey(chatId)) {
                activeGames.put(chatId, new Game(chatId));
            }

            game = activeGames.get(chatId);
            project = game.getProject();

            UserInteractionBuilder secondEntry = new UserInteractionBuilder(chatId)
                    .withName("Начать спринт")
                    .withRequest("Начинаем спринт...");

            UserInteractionBuilder firstEntry = new UserInteractionBuilder(chatId)
                    .withName("Планирование спринта")
                    .withRequest("something")
                    .withMenu(secondEntry);

            UserInteractionBuilder startGame = new UserInteractionBuilder(chatId)
                    .withName("/start")
                    .withRequest("Добро пожаловать в Hellgile!")
                    .withRequest("Внимание! \uD83D\uDD1E! ИГРА СОДЕРЖИТ НЕЦЕНЗУРНУЮ ЛЕКСИКУ.")
                    .withRequest("Твой новый проект: " + project.getName())
                    .withRequest("Деньги: " + project.getMoney() + " рублей")
                    .withRequest("Длительность: " + project.getDuration() + " дней")
                    .withRequest("Объём работ: 50 SP")
                    .withMenu(firstEntry)
                    .withMenu(secondEntry);

            UserInteractionBuilder interaction;

            if(interactions.get(chatId) == null) {
                interaction = startGame;
                interactions.put(chatId, interaction);
            } else {
                interaction = interactions.get(chatId);
            }

            try {
                for(SendMessage message : interaction.build(update)) {
                    execute(message);
                }

                //execute(activeGames.get(chatId).action(update));
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
