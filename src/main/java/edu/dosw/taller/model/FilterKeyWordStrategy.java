package edu.dosw.taller.model;

public class FilterKeyWordStrategy implements FilterStrategy {
    private String keyword;

    public FilterKeyWordStrategy(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(keyword) || task.getDescription().contains(keyword))
                .toList();
    }
}