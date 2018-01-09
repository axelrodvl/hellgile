package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.MainMenu;
import co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.management.ManageEmployee;
import co.axelrod.hellgile.model.project.Project;
import co.axelrod.hellgile.model.project.employees.AbstractEmployee;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class Management extends UserInteractionBuilder {
    public Management(Long chatId) {
        super(chatId, MainMenu.class);
        this
                .withName("Управление командой")
                .withRequest("Управление командой");

        /*
        Project project = projectStorage.get(this.chatId);

        for(AbstractEmployee employee : project.getEmployees()) {
            this.withMenu(new ManageEmployee(chatId, Management.class, employee));
        }
        */
    }
}
