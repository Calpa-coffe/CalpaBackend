package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Users;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendancesDTO {

    private int id;
    private Boolean attendance;
    private LocalDateTime datestarting;

    private Users users;
}
