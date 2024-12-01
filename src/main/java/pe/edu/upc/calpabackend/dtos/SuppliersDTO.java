package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuppliersDTO {
    private int id;
    private String namesupplier;
    private String phonesupplier;
    private String email;

}
