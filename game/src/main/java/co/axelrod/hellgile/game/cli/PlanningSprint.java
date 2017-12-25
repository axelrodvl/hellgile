package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.management.Project;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
public class PlanningSprint extends AbstractMenu {
    public PlanningSprint(Project project) {
        this.project = project;

        this.withInput = true;
        this.description = "Планирование спринта";
        this.exitMessage = "Начать спринт";

        outputStrings.add("--------------------");
        outputStrings.add("Планирование спринта");
        outputStrings.add("--------------------");
        outputStrings.add(project.toString());
        outputStrings.add("--------------------");


        //submenus.add(new Status(project));
        submenus.add(new ManageTeam(project));

        /*
        outputStrings.add("Статус");
        outputStrings.add("Управление командой");
        outputStrings.add("HR");
        outputStrings.add("Другое");
        outputStrings.add("Начать спринт");
        */
    }
}
