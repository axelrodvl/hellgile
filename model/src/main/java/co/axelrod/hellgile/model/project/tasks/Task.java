package co.axelrod.hellgile.model.project.tasks;

import co.axelrod.hellgile.model.project.employees.params.Grade;
import co.axelrod.hellgile.model.project.tasks.params.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 11.12.2017.
 */
@Getter
@Setter
public abstract class Task {
    private String name;
    private Integer storyPoints;
    private Grade workerGrade;
    private Status status;

    public Task () {
        // TODO calculate random task
    }
}
