package co.axelrod.hellgile.model.employees;

import co.axelrod.hellgile.model.employees.params.Grade;
import co.axelrod.hellgile.model.employees.params.Skill;
import co.axelrod.hellgile.model.employees.params.Temperament;
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
public abstract class AbstractEmployee {
    private String name = null;
    private List<Skill> skills = new ArrayList<>();
    private Temperament temperament = null;
    private Integer salary = 0;
    private Integer experience = 0;
    private Integer mood = 100;



    //public abstract void doTask(Task task);

    public Integer getPrice(Integer md) {
        return Math.round(salary / 21) * md;
    }

    public Grade getGrade() {
        if(experience < 100) {
            return Grade.JUNIOR;
        }
        if(experience < 200) {
            return Grade.MIDDLE;
        }
        if(experience < 300) {
            return Grade.SENIOR;
        }
        return Grade.LEAD;
    }

    @Override
    public String toString() {
        return "--------------------\n"
                + (name + ", " + getGrade() + " " + this.getClass().getSimpleName() + "\n")
                + ("--------------------\n")
                + ("Зарплата: " + salary + "\n")
                + ("Уровень: " + experience + "\n")
                + ("Настроение: " + mood + "\n");
    }
}