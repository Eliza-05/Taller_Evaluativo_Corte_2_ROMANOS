package edu.dosw.taller.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for creating a new task.
 */
@Data
@Schema(description = "DTO para crear una nueva tarea")
public class TaskCreateDTO {

    @Schema(description = "Tipo de tarea", example = "BASIC", required = true)
    private String type; //ESTO ES POR LO QUE HAY VARIOS TIPOS AUNQUE SOLO COLOCAMOS BASIC CON ESTO SE DEFINE EN EL FACTORY QUIEN LO INSTACIARA

    @Schema(description = "Título de la tarea", example = "Completar proyecto", required = true)
    private String title;

    @Schema(description = "Descripción detallada de la tarea", example = "Finalizar la implementación del módulo de usuarios")
    private String description;

    @Schema(description = "Fecha y hora de la tarea", example = "2025-09-28T10:30:00")
    private LocalDateTime date;

    @Schema(description = "Usuario creador de la tarea", example = "juan", required = true)
    private String creator;
}
