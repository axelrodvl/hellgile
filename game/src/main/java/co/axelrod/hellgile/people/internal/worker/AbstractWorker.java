package co.axelrod.hellgile.people.internal.worker;

import co.axelrod.hellgile.people.internal.worker.params.Temperament;
import co.axelrod.hellgile.people.internal.worker.params.WorkerGrade;
import co.axelrod.hellgile.people.skills.AbstractSkill;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */

@Getter
@Setter
public abstract class AbstractWorker {
    private String name = null;
    private List<AbstractSkill> skills = new ArrayList<AbstractSkill>();
    private Temperament temperament = null;
    private Integer salary = 0;
    private Integer level = 0;
    private Integer mood = 100;


    public WorkerGrade getWorkerGrade() {
        if(level < 100) {
            return WorkerGrade.JUNIOR;
        }
        if(level < 200) {
            return WorkerGrade.MIDDLE;
        }
        if(level < 300) {
            return WorkerGrade.SENIOR;
        }
        return WorkerGrade.LEAD;
    }



    @Override
    public String toString() {
        return "--------------------\n"
                + (name + ", " + getWorkerGrade() + " " + this.getClass().getSimpleName() + "\n")
                + ("--------------------\n")
                + ("Зарплата: " + salary + "\n")
                + ("Уровень: " + level + "\n")
                + ("Настроение: " + mood + "\n");
    }

    public Integer getPrice(Integer md) {
        return Math.round(salary / 21) * md;
    }
}