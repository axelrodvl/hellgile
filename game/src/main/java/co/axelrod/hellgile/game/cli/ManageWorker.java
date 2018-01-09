package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.model.project.Project;
import co.axelrod.hellgile.model.project.employees.AbstractEmployee;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
public class ManageWorker extends AbstractMenu {
    private AbstractEmployee worker;

    public ManageWorker(Project project, AbstractEmployee worker) {
        this.project = project;
        this.worker = worker;

        this.withInput = true;
        this.description = "👶 " + worker.getName() + ", " + worker.getGrade() + " " + worker.getClass().getSimpleName();
        this.exitMessage = "Закончить";

        outputStrings.add("--------------------");
        outputStrings.add(worker.toString());
        outputStrings.add("--------------------");

        submenus.add(new GiveTasks(project, worker));
    }
}
