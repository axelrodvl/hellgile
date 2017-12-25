package co.axelrod.hellgile.game.cli;

import co.axelrod.hellgile.management.Project;
import co.axelrod.hellgile.people.internal.worker.AbstractWorker;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */

@Getter
@Setter
public abstract class AbstractMenu {
    Project project;
    AbstractWorker worker = null;

    protected String description = null;

    protected Boolean withInput = false;

    protected String exitMessage = null;

    protected List<String> outputStrings = new ArrayList<String>();

    protected List<AbstractMenu> submenus = new ArrayList<AbstractMenu>();

    protected Boolean isAction = false;

    public void show() {
        Boolean exit = false;

        while (!exit) {
            for (String outputString : outputStrings) {
                System.out.println(outputString);
            }
            System.out.println();

            if (!withInput) {
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Integer count = 0;
            for (AbstractMenu submenu : submenus) {
                System.out.println(++count + " - " + submenu.getDescription());
            }
            System.out.println(++count + " - \uD83D\uDC4C " + exitMessage);

            String answer;
            try {
                answer = br.readLine();
            } catch (Exception ex) {
                throw new RuntimeException("readLine not works!");
            }

            if (!verify(answer, count + 1)) {
                Boolean correct;
                do {
                    System.out.println("Что-то пошло не так, введи заново.");
                    System.out.println();

                    try {
                        answer = br.readLine();
                    } catch (Exception ex) {
                        throw new RuntimeException("readLine not works!");
                    }

                    correct = verify(answer, count + 1);
                } while (!correct);
            }

            if(Integer.valueOf(answer).equals(count)) {
                exit = true;
            } else {
                if(submenus.get(Integer.valueOf(answer) - 1).isAction) {
                    action(project, worker);
                } else {
                    submenus.get(Integer.valueOf(answer) - 1).show();
                }
            }
        }
    }

    static boolean verify(String answer, Integer maxValue) {
        Integer number;
        try {
            number = Integer.valueOf(answer);
        } catch (Exception ex) {
            return false;
        }
        return (number > 0 && number <= maxValue);
    }

    protected void action(Project project, AbstractWorker worker) {

    }
}
