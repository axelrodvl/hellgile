package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.hr;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.HumanResources;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class Hire extends UserInteractionBuilder {
    public Hire(Long chatId) {
        super(chatId, HumanResources.class);
        this
                .withName("Начать поиск сотрудника")
                .withRequest("Выберите параметры");
    }
}
