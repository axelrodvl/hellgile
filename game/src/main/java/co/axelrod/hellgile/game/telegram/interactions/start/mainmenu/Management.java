package co.axelrod.hellgile.game.telegram.interactions.start.mainmenu;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.MainMenu;
import co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.management.ManageEmployee;
import co.axelrod.hellgile.model.Project;
import co.axelrod.hellgile.model.employees.AbstractEmployee;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.lang.reflect.Constructor;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 29.12.2017.
 */
public class Management extends UserInteractionBuilder {
    Project project;

    public Management(Long chatId, Project project) {
        super(chatId, MainMenu.class);
        this
                .withName("Управление командой")
                .withRequest("Управление командой");

        for(AbstractEmployee employee : project.getEmployees()) {
            this.withMenu(new ManageEmployee(chatId, Management.class, employee));
        }
    }

    public UserInteractionBuilder withMenu(Class userInteractionBuilderClass, Project project) {
        try {
            Constructor<UserInteractionBuilder> constructor = userInteractionBuilderClass.getConstructor(Long.class);
            UserInteractionBuilder userInteractionBuilder = constructor.newInstance(chatId);

            this.nextInteractions.put(userInteractionBuilder.menuItemName, userInteractionBuilderClass);
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(userInteractionBuilder.menuItemName);
            this.keyboard.add(keyboardRow);
            return this;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
