package edu.dosw.taller.model;

import edu.dosw.taller.model.entities.Task;
import java.util.List;

/**
 * Strategy interface for filtering tasks based on different criteria.
 */
public interface FilterStrategy {
    List<Task> filter(List<Task> tasks);
}
