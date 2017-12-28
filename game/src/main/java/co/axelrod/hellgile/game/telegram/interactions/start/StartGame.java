package co.axelrod.hellgile.game.telegram.interactions.start;

import co.axelrod.hellgile.game.telegram.UserInteractionBuilder;
import co.axelrod.hellgile.game.telegram.interactions.start.mainmenu.Status;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 26.12.2017.
 */
public class StartGame extends UserInteractionBuilder {
    public StartGame(Long chatId) {
        super(chatId);
        this
                .withName("/start")
                .withRequest("Добро пожаловать в Hellgile!")
                .withRequest("Внимание! \uD83D\uDD1E! ИГРА СОДЕРЖИТ НЕЦЕНЗУРНУЮ ЛЕКСИКУ.")
                //.withRequest("Твой новый проект: " + project.getName())
                //.withRequest("Деньги: " + project.getMoney() + " рублей")
                //.withRequest("Длительность: " + project.getDuration() + " дней")
                .withRequest("Объём работ: 50 SP")
                .withMenu(Status.class);
    }
}
