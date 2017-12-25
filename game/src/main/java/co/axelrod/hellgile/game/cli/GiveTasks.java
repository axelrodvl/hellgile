package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.management.Project;
import co.axelrod.hellgile.people.internal.worker.AbstractWorker;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 12.12.2017.
 */
public class GiveTasks extends AbstractMenu {
    public GiveTasks(Project project, AbstractWorker worker) {
        this.project = project;
        this.worker = worker;
        this.withInput = false;
        this.description = "\uD83D\uDCBC Выдать задачи";
        this.isAction = true;
        //this.exitMessage = "Закончить";


    }

    @Override
    protected void action(Project project, AbstractWorker worker) {
        System.out.println("Вася получил задачу на 1 MD, стоимость: " + worker.getPrice(1));

        project.setMoney(project.getMoney() - worker.getPrice(1));
    }
}
