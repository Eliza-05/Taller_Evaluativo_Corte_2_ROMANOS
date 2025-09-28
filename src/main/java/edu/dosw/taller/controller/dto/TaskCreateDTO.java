package edu.dosw.taller.controller.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class TaskCreateDTO {
    private String type; //ESTO ES POR LO QUE HAY VARIOS TIPOS AUNQUE SOLO COLOCAMOS BASIC CON ESTO SE DEFINE EN EL FACTORY QUIEN LO INSTACIARA
    private String title;
    private String description;
    private LocalDateTime date;
    private String creator;
}
