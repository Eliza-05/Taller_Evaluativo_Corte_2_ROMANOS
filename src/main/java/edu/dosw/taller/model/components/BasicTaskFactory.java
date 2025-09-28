package edu.dosw.taller.model.components;

import edu.dosw.taller.model.entities.BasicTask;
import edu.dosw.taller.model.entities.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Concrete implementation of TaskFactory that creates BasicTask instances.
 */
@Component
public class BasicTaskFactory extends TaskFactory {

    /**
     * Creates a new BasicTask instance with the specified parameters.
     *
     * @param title the title or name of the task to be created
     * @param description detailed description of what the task involves
     * @param date the date and time when the task is created or scheduled
     * @param creator the username or identifier of the person creating the task
     * @return a new BasicTask instance initialized with the provided parameters
     */
    @Override
    public Task createTask(String title, String description, LocalDateTime date, String creator) {
        return new BasicTask(title, description, date, creator);
    }
}
