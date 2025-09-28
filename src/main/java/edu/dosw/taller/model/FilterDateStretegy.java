package edu.dosw.taller.model;

import java.time.LocalDateTime;
import java.util.List;
import edu.dosw.taller.model.entities.Task;

/**
 * FilterDateStretegy class that filter tasks by date range.
 */
public class FilterDateStretegy implements FilterStrategy{
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    /**
     * Constructor of FilterDateStretegy.
     * @param dateStart start date
     * @param dateEnd end date
     */
    public FilterDateStretegy(LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    /**
     * Filter tasks by date range.
     * @param tasks list of tasks to filter
     * @return list of filtered tasks
     */
    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getDate() != null &&
                        !task.getDate().isBefore(dateStart) &&
                        !task.getDate().isAfter(dateEnd))
                .toList();
    }
}

