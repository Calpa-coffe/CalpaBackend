package pe.edu.upc.calpabackend.dtos;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.entities.Users;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TicketsDTO {
    private int id;
    private String clientname;
    private LocalDate datepay;
    private Double total;
    private Double amountpayment;

    private List<TicketProductDTO> ticketProducts;

    private Users users;
    private Members members;
    private TypePayments typepayments;
}
