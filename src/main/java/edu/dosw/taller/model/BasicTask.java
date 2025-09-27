package edu.dosw.taller.model;

import edu.dosw.taller.model.entities.Task;

import java.time.LocalDateTime;

public class BasicTask extends Task {

    public BasicTask(String title, String description, LocalDateTime date, String creator) {
        super(title, description, date, creator);
    }

    public BasicTask() {
        super();
    }
}
