package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.StartGame;
import co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.hr.Hire;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class HumanResources extends UserInteractionBuilder {
    public HumanResources(Long chatId) {
        super(chatId, StartGame.class);
        this
                .withName("HR")
                .withRequest("Здесь вы можете нанимать новых сотрудников")
                .withMenu(Hire.class);
    }
}
