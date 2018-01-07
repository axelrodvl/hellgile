package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.management;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class GiveTask extends UserInteractionBuilder {
    public GiveTask(Long chatId, Class parentInteraction) {
        super(chatId, parentInteraction);
        this
                .withName("Распределение задач")
                .withRequest("Выберите сотрудника ");
    }
}
