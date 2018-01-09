package co.axelrod.hellgile.model.project;

import co.axelrod.hellgile.model.project.customer.Customer;
import co.axelrod.hellgile.model.project.employees.AbstractEmployee;
import co.axelrod.hellgile.model.project.employees.Developer;
import co.axelrod.hellgile.model.project.tasks.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */

@Getter
@Setter
public class Project {
    private static final String CURRENCY = "RUB";

    private String name;

    private Integer duration = 0;
    private List<AbstractEmployee> employees = new ArrayList<AbstractEmployee>();

    private Integer currentSprint = 0;
    private Integer daysLeft;

    private Integer valuation;
    private Integer money;

    private Customer customer;

    private Integer storyPoints = 0;
    private List<Task> tasksToDo = new ArrayList<Task>();
    private List<Task> tasksDone = new ArrayList<Task>();

    private Integer notFoundBugs = 0;

    private Integer foundBugs = 0;

    private Integer fuckedUpFee = Integer.MAX_VALUE;

    @Override
    public String toString() {
        return  "--------------------\n"
                + ("Проект: " + name + "\n")
                + ("--------------------\n")
                + ("\uD83D\uDCBC Заказчик: " + customer.getName() + "\n")
                + ("\uD83D\uDCDD Статус: " + (isDone() ? "завершен" : "в работе") + "\n")
                + ("\uD83D\uDD70 Расчетная длительность: " + duration + "\n")
                + ("\uD83D\uDD52 Дней осталось: " + daysLeft + "\n")
                + ("\uD83D\uDC82 Команда: " + employees.size() + " человек" + "\n")
                + ("☑️ Текущий спринт: " + currentSprint + "\n")
                + ("\uD83D\uDCB0 Осталось денег: " + money + " рублей" + "\n")
                + ("✏️ Осталось задач: " + tasksToDo.size() + "\n")
                + ("✏️ Выполнено задач: " + tasksToDo.size() + "\n")
                + ("\uD83D\uDC1B Известных багов: " + foundBugs);
    }

    public Boolean isDone() {
        return false;
    }

    Project() {
        // TODO calculate balance

        // Generate new project
        name = "Интеграционная шина";
        duration = generateNumber(60, 365);
        daysLeft = duration;
        valuation = generateNumber(1000000, 10000000);

        storyPoints = generateNumber(200, 3000);

        // Fill tasks list by story points
        /*
        Integer storyPointsToGenerate = storyPoints;
        while(storyPointsToGenerate != 0) {
            Task taskToDo = new Task();

            if(storyPointsToGenerate - taskToDo.getStoryPoints() < 0) {
                taskToDo.setStoryPoints(storyPointsToGenerate);
            }

            tasksToDo.add(taskToDo);
            storyPointsToGenerate -= taskToDo.getStoryPoints();
        }

        Developer developer = new Developer();
        employees.add(developer);
*/






    }

    private Integer generateNumber(Integer from, Integer to) {
        // TODO
        return 1000000;
    }
}
