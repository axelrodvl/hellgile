package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.model.Project;
import co.axelrod.hellgile.model.employees.AbstractEmployee;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
public class ManageTeam extends AbstractMenu {
    public ManageTeam(Project project) {
        this.project = project;

        this.withInput = true;
        this.description = "\uD83D\uDC82 Управление командой";
        this.exitMessage = "Закончить";

        outputStrings.add("--------------------");
        outputStrings.add("Управление командой");
        outputStrings.add("--------------------");

        for(AbstractEmployee worker : project.getEmployees()) {
            submenus.add(new ManageWorker(project, worker));
        }
    }
}
