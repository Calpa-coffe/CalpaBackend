package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private int id;
    private String document;
    private String clientname;
    private String phoneclient;
    private String email;
}
