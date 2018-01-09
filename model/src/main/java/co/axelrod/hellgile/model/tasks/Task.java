package co.axelrod.hellgile.model.tasks;

import co.axelrod.hellgile.model.employees.params.Grade;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
@Getter
@Setter
public class Task {
    private String name;
    private Integer storyPoints;
    private Grade workerGrade;

    public Task () {
        // TODO calculate random task
    }
}
