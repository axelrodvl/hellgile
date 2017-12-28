package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.StartGame;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class Management extends UserInteractionBuilder {
    public Management(Long chatId) {
        super(chatId, StartGame.class);
        this
                .withName("Управление командой")
                .withRequest("Здесь вы можете раздавать задачи и управлять сотрудниками");
    }
}
