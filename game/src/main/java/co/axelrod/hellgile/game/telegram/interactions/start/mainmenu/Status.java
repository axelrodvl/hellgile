package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.StartGame;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class Status extends UserInteractionBuilder {
    public Status(Long chatId) {
        super(chatId);
        this
                .withName("Статус")
                .withRequest("Текущий статус проекта:")
                .withRequest("Объём работ: 50 SP");
    }
}
