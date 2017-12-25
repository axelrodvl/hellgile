package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.management.Project;
import co.axelrod.hellgile.people.internal.worker.AbstractWorker;

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

        for(AbstractWorker worker : project.getWorkers()) {
            submenus.add(new ManageWorker(project, worker));
        }
    }
}
