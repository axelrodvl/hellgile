package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.management.Project;
import co.axelrod.hellgile.people.internal.worker.AbstractWorker;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
public class ManageWorker extends AbstractMenu {
    private AbstractWorker worker;

    public ManageWorker(Project project, AbstractWorker worker) {
        this.project = project;
        this.worker = worker;

        this.withInput = true;
        this.description = "👶 " + worker.getName() + ", " + worker.getWorkerGrade() + " " + worker.getClass().getSimpleName();
        this.exitMessage = "Закончить";

        outputStrings.add("--------------------");
        outputStrings.add(worker.toString());
        outputStrings.add("--------------------");

        submenus.add(new GiveTasks(project, worker));
    }
}
