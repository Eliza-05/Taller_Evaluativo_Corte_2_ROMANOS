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

    protected Task(String title, String description, LocalDateTime date, String creator) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.date = date;
        this.creator = creator;
        this.status = TaskStatus.PENDING;
    }

    protected Task() {}
}
