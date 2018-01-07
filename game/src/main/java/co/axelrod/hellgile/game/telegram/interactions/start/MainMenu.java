package co.axelrod.hellgile.game.telegram.interactions.start;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.*;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 26.12.2017.
 */
public class MainMenu extends UserInteractionBuilder {
    public MainMenu(Long chatId) {
        super(chatId, null);
        this
                .withName("start")
                .withRequest("Добро пожаловать в Hellgile!")
                //.withRequest("Твой новый проект: " + project.getName())
                //.withRequest("Деньги: " + project.getMoney() + " рублей")
                //.withRequest("Длительность: " + project.getDuration() + " дней")
                .withRequest("Объём работ: 50 SP")
                .withMenu(Status.class)
                .withMenu(Management.class)
                .withMenu(HumanResources.class)
                .withMenu(StartSprint.class);
    }
}
