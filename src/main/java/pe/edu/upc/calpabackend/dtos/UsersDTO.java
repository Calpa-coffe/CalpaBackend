package pe.edu.upc.calpabackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;
}
