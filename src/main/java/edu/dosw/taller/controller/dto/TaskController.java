package edu.dosw.taller.controller.dto;
import edu.dosw.taller.model.entities.Task;
import edu.dosw.taller.model.entities.TaskStatus;
import edu.dosw.taller.model.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * REST controller for task management.
 * Provides CRUD operations and filtering endpoints.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * Creates a new task.
     *
     * @param dto data for creating a task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TaskCreateDTO dto) {
        service.createTask(dto);
    }

    /**
     * Retrieves all tasks.
     *
     * @return list of all tasks
     */
    @GetMapping
    public List<TaskResponseDTO> listAll() {
        return service.listTasks().stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Retrieves all completed tasks.
     *
     * @return list of completed tasks
     */
    @GetMapping("/completed")
    public List<TaskResponseDTO> getCompleted() {
        return service.filterCompleted().stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Searches tasks by keyword.
     *
     * @param keyword text to search in title/description
     * @return list of tasks matching the keyword
     */
    @GetMapping("/search")
    public List<TaskResponseDTO> searchByKeyword(@RequestParam String keyword) {
        return service.searchByKeyword(keyword).stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Counts tasks by status.
     *
     * @param status task status
     * @return number of tasks with given status
     */
    @GetMapping("/count")
    public long countByStatus(@RequestParam String status) {
        return service.getTaskCountByStatus(TaskStatus.valueOf(status.toUpperCase()));
    }

    /**
     * Retrieves tasks created by a specific user.
     *
     * @param creator username of task creator
     * @return list of tasks created by the user
     */
    @GetMapping("/creator/{creator}")
    public List<TaskResponseDTO> getByCreator(@PathVariable String creator) {
        return service.getTasksByCreator(creator).stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Updates the status of a task.
     *
     * @param id  task id
     * @param dto new status
     */
    @PutMapping("/{id}/status")
    public void updateStatus(
            @PathVariable String id,
            @RequestBody TaskUpdateStatusDTO dto) {
        service.updateStatus(id, dto.getStatus());
    }

    /**
     * Deletes a task by id.
     *
     * @param id task id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.deleteTask(id);
    }
}


