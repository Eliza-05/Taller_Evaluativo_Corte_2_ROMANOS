package edu.dosw.taller.model.components;

import edu.dosw.taller.model.entities.BasicTask;
import edu.dosw.taller.model.entities.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BasicTaskFactory extends TaskFactory {

    @Override
    public Task createTask(String title, String description, LocalDateTime date, String creator) {
        return new BasicTask(title, description, date, creator);
    }
}
