package edu.dosw.taller.model;

import edu.dosw.taller.model.entities.Task;
import java.util.List;

/**
 * FilterKeyWordStrategy filters a list of tasks based on a keyword in their title or description.
 */
public class FilterKeyWordStrategy implements FilterStrategy {
    private String keyword;

    /**
     * Constructs a FilterKeyWordStrategy with the specified keyword.
     *
     * @param keyword the keyword to filter by in task titles and descriptions
     */
    public FilterKeyWordStrategy(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Filters the given list of tasks, returning only those that contain the specified keyword
     * in their title or description.
     *
     * @param tasks the list of tasks to filter
     * @return a list of tasks that contain the specified keyword in their title or description
     */
    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(keyword) || task.getDescription().contains(keyword))
                .toList();
    }
}