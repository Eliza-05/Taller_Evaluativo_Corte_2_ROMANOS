package edu.dosw.taller.model.components;

import edu.dosw.taller.model.entities.Task;
import java.time.LocalDateTime;

public abstract class TaskFactory {
    /**
     * Factory Method - debe ser implementado por las subclases
     * @param title título de la tarea
     * @param description descripción de la tarea
     * @param date fecha de la tarea
     * @param creator creador de la tarea
     * @return nueva instancia de Task
     */
    public abstract Task createTask(String title, String description, LocalDateTime date, String creator);
}