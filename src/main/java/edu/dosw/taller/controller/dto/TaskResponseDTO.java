package edu.dosw.taller.controller.dto;

import edu.dosw.taller.model.entities.Task;
import edu.dosw.taller.model.entities.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for task response.
 */
@Data
@Builder
@AllArgsConstructor
@Schema(description = "DTO de respuesta que contiene los datos de una tarea")
public class TaskResponseDTO {

    @Schema(description = "ID único de la tarea", example = "1")
    private String id;

    @Schema(description = "Título de la tarea", example = "Completar proyecto")
    private String title;

    @Schema(description = "Descripción de la tarea", example = "Finalizar la implementación del módulo de usuarios")
    private String description;

    @Schema(description = "Fecha y hora de la tarea", example = "2025-09-28T10:30:00")
    private LocalDateTime date;

    @Schema(description = "Usuario creador de la tarea", example = "juan")
    private String creator;

    @Schema(description = "Estado actual de la tarea", example = "PENDING")
    private TaskStatus status;


    /**
     * Converts a Task entity to a TaskResponseDTO.
     * @param task the Task entity to convert
     * @return the corresponding TaskResponseDTO
     */
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
