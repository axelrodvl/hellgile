package co.axelrod.hellgile.game;

import co.axelrod.hellgile.management.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractAction {
    private String description;
    private Double chanceOfSuccess = 1D;
    private Double chanceOfOccur = 1D;

    public void fireAction(Project project) {
        if(isHappening()) {
            if(isSuccess()) {
                makeAction(project);
            } else {
                makeFailAction(project);
            }
        }
    }

    private Boolean isHappening() {
        return chanceOfOccur - Math.random() > 0;
    }

    private Boolean isSuccess() {
        return chanceOfSuccess - Math.random() > 0;
    }

    abstract void makeAction(Project project);

    abstract void makeFailAction(Project project);
}
