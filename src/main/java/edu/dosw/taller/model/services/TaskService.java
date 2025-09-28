package edu.dosw.taller.model.services;

import edu.dosw.taller.model.FilterStrategy;
import edu.dosw.taller.model.components.TaskFactory;
import edu.dosw.taller.model.entities.Task;
import edu.dosw.taller.model.entities.TaskStatus;
import edu.dosw.taller.model.persistence.repository.TaskRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskFactory taskFactory;
    private FilterStrategy filterStrategy;

    public TaskService(TaskRepository taskRepository, TaskFactory taskFactory) {
        this.taskRepository = taskRepository;
        this.taskFactory = taskFactory;
    }

    /**
     * Creates a new task using the TaskFactory and saves it to the database.
     *
     * @param title       the task title
     * @param description the task description
     * @param date        the task due date
     * @param user        the user who created the task
     * @return the created and saved task
     */
    @Transactional
    public Task createTask(String title, String description, LocalDateTime date, String user) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }
        if (user == null || user.trim().isEmpty()) {
            throw new IllegalArgumentException("User cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Task date cannot be null");
        }

        try {
            Task task = taskFactory.createTask(title.trim(), description != null ? description.trim() : "", date, user.trim()); //Se ponen defaults por si acaso
            return taskRepository.save(task);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create task", e);
        }
    }

    /**
     * Retrieves all tasks from the database.
     *
     * @return list of all tasks
     */
    public List<Task> listTasks() {
        try {
            return taskRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("Failed to list tasks", e);
        }
    }

    /**
     * Filters tasks using the Strategy pattern.
     *
     * @param filter the filtering strategy to apply
     * @return filtered list of tasks
     */
    public List<Task> filterTasks(FilterStrategy filter) {
        if (filter == null) {
            throw new IllegalArgumentException("Filter strategy cannot be null");
        }
        try {
            List<Task> allTasks = taskRepository.findAll();
            List<Task> filteredTasks = filter.filter(allTasks);
            return filteredTasks;

        } catch (Exception e) {
            throw new RuntimeException("Failed to filter tasks", e);
        }
    }

    /**
     * Retrieves all completed tasks.
     *
     * @return list of completed tasks
     */
    public List<Task> filterCompleted() {
        try {
            return taskRepository.findByStatus(TaskStatus.COMPLETED);

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve completed tasks", e);
        }
    }

    /**
     * Updates the status of a task.
     *
     * @param id     the task ID
     * @param status the new status
     * @return the updated task
     * @throws RuntimeException if task not found or update fails
     */
    @Transactional
    public Task updateStatus(String id, TaskStatus status) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be null or empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }
        try {
            Task task = taskRepository.findById(id.trim()).orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
            task.setStatus(status);
            return taskRepository.save(task);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update task status", e);
        }
    }

    /**
     * Searches for a task by its ID.
     *
     * @param id the task ID to search for
     * @return Optional containing the task if found, empty otherwise
     */
    public Optional<Task> searchTaskById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Optional.empty();
        }

        try {
            return taskRepository.findById(id.trim());

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id the task ID to delete
     * @return true if task was deleted, false if not found
     */
    @Transactional
    public boolean deleteTask(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        try {
            Optional<Task> task = taskRepository.findById(id.trim());
            if (task.isPresent()) {
                taskRepository.deleteById(id.trim());
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete task", e);
        }
    }

    /**
     * Sets the filtering strategy for the Strategy pattern.
     *
     * @param filterStrategy the strategy to set
     */
    public void setFilterStrategy(FilterStrategy filterStrategy) {
        if (filterStrategy == null) {
            throw new IllegalArgumentException("Filter strategy cannot be null");
        }
        this.filterStrategy = filterStrategy;
    }

    /**
     * Gets tasks by creator using the repository method.
     *
     * @param creator the username of the task creator
     * @return list of tasks created by the user
     */
    public List<Task> getTasksByCreator(String creator) {
        if (creator == null || creator.trim().isEmpty()) {
            throw new IllegalArgumentException("Creator cannot be null or empty");
        }
        return taskRepository.findByCreator(creator.trim());
    }

    /**
     * Searches tasks by keyword using the repository's custom query.
     *
     * @param keyword the keyword to search for
     * @return list of tasks containing the keyword
     */
    public List<Task> searchByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        return taskRepository.findByKeyword(keyword.trim());
    }

    /**
     * Gets count of tasks by status.
     *
     * @param status the task status
     * @return count of tasks with the specified status
     */
    public long getTaskCountByStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return taskRepository.countByStatus(status);
    }
}