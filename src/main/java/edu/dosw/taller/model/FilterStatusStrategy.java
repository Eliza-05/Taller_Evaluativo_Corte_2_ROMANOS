package edu.dosw.taller.model;

import edu.dosw.taller.model.entities.*;
import java.util.List;


/**
 * FilterStatusStrategy filters a list of tasks based on their status.
 */
public class FilterStatusStrategy implements FilterStrategy {
    private TaskStatus status;

    /**
     * Constructs a FilterStatusStrategy with the specified task status.
     *
     * @param status the TaskStatus to filter by (e.g., PENDING, IN_PROGRESS, COMPLETED)
     */
    public FilterStatusStrategy(TaskStatus status) {
        this.status = status;
    }

    /**
     * Filters the given list of tasks, returning only those that match the specified status.
     * @param tasks the list of tasks to filter
     * @return a list of tasks that have the specified status
     */
    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(this.status))
                .toList();
    }
}
