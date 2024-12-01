package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.entities.Users;

import java.time.LocalDate;

@Getter
@Setter
public class TicketsDTO {
    private int id;
    private String clientname;
    private LocalDate datepay;
    private Double total;

    private Products product;

    private Users users;

    private Members members;

    private TypePayments typepayments;
}