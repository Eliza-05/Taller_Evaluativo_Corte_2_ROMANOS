package edu.dosw.taller.controller.dto;

import edu.dosw.taller.model.entities.Task;
import edu.dosw.taller.model.entities.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TaskResponseDTO {
    private String id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String creator;
    private TaskStatus status;

    public static TaskResponseDTO fromEntity(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .date(task.getDate())
                .creator(task.getCreator())
                .status(TaskStatus.valueOf(task.getStatus().name()))
                .build();
    }
}
