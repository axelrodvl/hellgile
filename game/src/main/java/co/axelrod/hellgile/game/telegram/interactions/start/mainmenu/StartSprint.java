package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.MainMenu;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class StartSprint extends UserInteractionBuilder {
    public StartSprint(Long chatId) {
        super(chatId, MainMenu.class);
        this
                .withName("Начать спринт")
                .withRequest("Начинаем спринт...");
    }
}
