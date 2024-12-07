package pe.edu.upc.calpabackend.dtos;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Roles;

import java.util.List;

@Getter
@Setter
public class UsersDTO {
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;
    private List<Roles> roles;

}
