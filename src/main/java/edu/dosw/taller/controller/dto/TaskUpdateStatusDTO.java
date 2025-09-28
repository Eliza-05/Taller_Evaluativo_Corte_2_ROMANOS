package edu.dosw.taller.controller.dto;

import edu.dosw.taller.model.entities.TaskStatus;
import lombok.Data;

@Data
public class TaskUpdateStatusDTO {
    TaskStatus status;
}
