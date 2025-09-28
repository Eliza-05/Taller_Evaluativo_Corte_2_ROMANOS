package edu.dosw.taller.model.entities;

import java.time.LocalDateTime;

/**
 * Concrete implementation of the Task class representing a basic task type.
 */
public class BasicTask extends Task {

    /**
     * Creates a new BasicTask with the specified parameters.
     * Works with the parent Task constructor to initialize all fields
     * and set the initial status to PENDING.
     *
     * @param title the title or name of the task
     * @param description detailed description of what the task involves
     * @param date the date and time when the task was created or scheduled
     * @param creator the username or identifier of the person who created the task
     */
    public BasicTask(String title, String description, LocalDateTime date, String creator) {
        super(title, description, date, creator);
    }

    /**
     * Default protected constructor for framework use (MongoDB).
     */
    public BasicTask() {
        super();
    }
}
