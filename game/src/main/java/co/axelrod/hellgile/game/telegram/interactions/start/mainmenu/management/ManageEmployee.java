package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.management;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.model.employees.AbstractEmployee;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class ManageEmployee extends UserInteractionBuilder {
    AbstractEmployee employee;

    public ManageEmployee(Long chatId, Class parentInteraction, AbstractEmployee employee) {
        super(chatId, parentInteraction);
        this.employee = employee;
    }
}
