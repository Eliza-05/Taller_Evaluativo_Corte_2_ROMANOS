package edu.dosw.taller.model.components;

import edu.dosw.taller.model.entities.Task;
import java.time.LocalDateTime;

/**
 * Abstract factory class implementing the Factory Method pattern for Task creation.
 * This class defines the way  to create Task instances while allowing subclasses to determine the specific type of Task to instantiate.
 */
public abstract class TaskFactory {

    /**
     * Factory Method that must be implemented by concrete subclasses to create Task instances.
     * Allow different factories to create different types of tasks based on the same abstract class.
     *
     * @param title the title or name of the task to be created
     * @param description detailed description of what the task involves
     * @param date the date and time when the task is created or scheduled
     * @param creator the username or identifier of the person creating the task
     * @return a new Task instance of the specific type determined by the concrete factory
     */
    public abstract Task createTask(String title, String description, LocalDateTime date, String creator);
}