package edu.dosw.taller.controller.dto;

import edu.dosw.taller.model.entities.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskFilterDTO {
    private String keyword;
    private TaskStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}