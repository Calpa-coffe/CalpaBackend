package pe.edu.upc.calpabackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketProductDTO {
    private int id;
    private ProductDTO product;
    private int quantity;
}
