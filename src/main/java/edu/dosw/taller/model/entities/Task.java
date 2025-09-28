package edu.dosw.taller.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "task")
public abstract class Task {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String description;
    @Field
    private LocalDateTime date;
    @Field
    private String creator;
    @Field
    private TaskStatus status;

    /**
     * Protected constructor for creating a new task with specified parameters.
     * Automatically generates a unique ID and sets initial status to PENDING.
     *
     * @param title the title of the task
     * @param description detailed description of the task
     * @param date the date and time associated with the task
     * @param creator the username of the task creator
     */
    protected Task(String title, String description, LocalDateTime date, String creator) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.date = date;
        this.creator = creator;
        this.status = TaskStatus.PENDING;
    }

    /**
     * Default protected constructor for framework use (MongoDB).
     */
    protected Task() {}
}
