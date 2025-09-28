package edu.dosw.taller.controller.dto;

import edu.dosw.taller.model.entities.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Data Transfer Object for updating the status of a task.
 */
@Data
@Schema(description = "DTO para actualizar el estado de una tarea")
public class TaskUpdateStatusDTO {

    @Schema(description = "Nuevo estado de la tarea",
            example = "COMPLETED",
            allowableValues = {"PENDING", "IN_PROGRESS", "COMPLETED"},
            required = true)
    private TaskStatus status;
}
