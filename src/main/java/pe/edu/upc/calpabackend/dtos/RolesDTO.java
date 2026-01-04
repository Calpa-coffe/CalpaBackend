package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Users;

@Getter
@Setter
public class RolesDTO {
    private Long id;

    private String rol;

    private Users user;
}
