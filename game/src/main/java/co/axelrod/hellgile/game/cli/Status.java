package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.model.Project;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
public class Status extends AbstractMenu {
    public Status(Project project) {
        outputStrings.add("--------------------");
        outputStrings.add("Статус проекта:");
        outputStrings.add("--------------------");
        outputStrings.add(project.toString());

        withInput = false;
        description = "Статус";
    }
}
