package edu.dosw.taller.controller.dto;
import edu.dosw.taller.model.entities.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }
//C
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TaskCreateDTO dto) {
        service.createTask(dto);
    }
//R
    @PostMapping("/filter")
    public List<TaskResponseDTO> filter(@RequestBody TaskFilterDTO filterParams) {
        return service.filter(filterParams);
    }
//U
    @PutMapping("/{id}/status")
    public void updateStatus(
            @PathVariable String id,
            @RequestBody TaskUpdateStatusDTO dto) {
        service.updateStatus(id, dto.getStatus());
    }
//D
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content
    public void delete(@PathVariable String id) {
        service.deleteTask(id);
    }

}
