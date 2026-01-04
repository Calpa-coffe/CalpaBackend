package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Suppliers;
import pe.edu.upc.calpabackend.entities.Users;

@Getter
@Setter
public class DetailSupplierDTO {
    private int id;
    private String description;
    private Double totalprice;

    private Suppliers supplier;

    private Users users;
}
