package edu.dosw.taller.model;

public class FilterStatusStrategy implements FilterStrategy {
    private TaskStatus status;

    public FilterStatusStrategy(status TaskStatus) {
        this.status = status;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        return;
    }
}
