package edu.dosw.taller.controller.dto;

import edu.dosw.taller.model.entities.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for filtering tasks by various criteria.
 */
@Data
@Schema(description = "DTO para filtrar tareas por diferentes criterios")
public class TaskFilterDTO {

    @Schema(description = "Palabra clave para buscar en título o descripción", example = "proyecto")
    private String keyword;

    @Schema(description = "Estado de la tarea para filtrar", example = "PENDING")
    private TaskStatus status;

    @Schema(description = "Fecha de inicio del rango de búsqueda", example = "2025-09-01T00:00:00")
    private LocalDateTime startDate;

    @Schema(description = "Fecha de fin del rango de búsqueda", example = "2025-09-28T23:59:59")
    private LocalDateTime endDate;
}
