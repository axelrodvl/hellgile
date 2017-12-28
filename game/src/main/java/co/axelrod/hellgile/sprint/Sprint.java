package co.axelrod.hellgile.sprint;

import co.axelrod.hellgile.game.cli.PlanningSprint;
import co.axelrod.hellgile.model.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
@Getter
@Setter
public class Sprint {
    private Project project;

    public Sprint(Project project) {
        this.project = project;
    }

    private String name;

    // Days
    private Integer duration;
    private Integer tasks;
    private Integer doneTasks;

    public void planSprint() {
        System.out.println();
        System.out.println("Планируем спринт");
        System.out.println(name);
        System.out.println("\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC\uD83D\uDCCC");
        System.out.println();
        //new PlanningSprint(project).show();
    }

    public void runSprint() {
        System.out.println();
        System.out.println("Начинаем спринт");
        System.out.println(name);

        for(Integer i = 0; i < 20; i++) {
            try {
                System.out.print("\uD83D\uDD25");
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception ex) {
                throw new RuntimeException("TimeUnit.MILLISECONDS.sleep not working!");
            }
        }
        System.out.println();

        System.out.println();
        project.setDaysLeft(project.getDaysLeft() - 1);
        project.setCurrentSprint(project.getCurrentSprint() + 1);

        // Change state
        // Make actions
        // Calculate events
        // Fire events
        // Show end
        // Print statistics
    }

    public void endSprint() {
        System.out.println();
        System.out.println("Завершаем спринт");
        System.out.println(name);
        System.out.println("\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2\uD83D\uDDD2");
        System.out.println();
        // Calculate money

        System.out.println();
        for(Integer i = 0; i < 50; i++) {
            System.out.print("\uD83D\uDE0E");
        }

        for(Integer i = 0; i < 23; i++) {
            System.out.println();
        }
    }
}
