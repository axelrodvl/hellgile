package co.axelrod.hellgile.model;

import co.axelrod.hellgile.model.customer.Customer;
import co.axelrod.hellgile.model.employees.AbstractEmployee;
import co.axelrod.hellgile.model.tasks.Task;
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
    private String name;
    private Integer valuation = 0;
    private Integer duration = 0;
    private List<AbstractEmployee> employees = new ArrayList<AbstractEmployee>();

    private Integer currentSprint = 0;
    private Integer daysLeft = 0;

    private Integer money = 0;

    private Customer customer;

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
}
