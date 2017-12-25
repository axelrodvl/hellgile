package co.axelrod.hellgile.game.telegram.interactions;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 26.12.2017.
 */
public class StartGame extends UserInteractionBuilder {
    public StartGame(Long chatId) {
        super(chatId);
    }
}
