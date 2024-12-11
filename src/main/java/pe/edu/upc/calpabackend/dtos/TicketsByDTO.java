package pe.edu.upc.calpabackend.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
public class TicketsByDTO {
    private String datepay;
    private Double total;
}
