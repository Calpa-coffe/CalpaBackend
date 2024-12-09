package pe.edu.upc.calpabackend.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.exception.ResourceNotFoundException;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.repositories.ITicketsRepository;
import pe.edu.upc.calpabackend.repositories.ITypePaymentsRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.ITicketsServices;

import java.util.List;

@Service
public class TicketsServicesImplements implements ITicketsServices {
    @Autowired
    private ITicketsRepository iR;
    @Autowired
    private ITypePaymentsRepository iT;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Tickets insert(Tickets tickets) {
        // Validar usuario
        if (tickets.getUsers() == null || tickets.getUsers().getId() <= 0) {
            throw new ResourceNotFoundException("Usuario no proporcionado o inválido");
        }

        // Validar tipo de pago
        if (tickets.getTypepayments() == null || tickets.getTypepayments().getId() <= 0) {
            throw new ResourceNotFoundException("Tipo de pago no proporcionado o inválido");
        }

        // Buscar tipo de pago desde la base de datos
        TypePayments typePayment = iT.findById(tickets.getTypepayments().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de pago no encontrado"));

        tickets.setTypepayments(typePayment);

        // Validar que los productos tengan cantidades y calcular el total
        double total = 0.0;
        if (tickets.getProduct() != null) {
            for (Products product : tickets.getProduct()) {
                total += product.getPrice() * tickets.getQuantity();
            }
        }
        tickets.setTotal(total);

        // Si no hay miembro asociado, solo guarda el nombre del cliente
        if (tickets.getMembers() == null) {
            tickets.setMembers(null);
        }

        // Guardar ticket
        return iR.save(tickets);
    }



    @Override
    public List<Tickets> list() {
        return iR.findAll();
    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);
    }

    @Override
    public void update(Tickets tickets) {
        iR.save(tickets);
    }

    @Override
    public TicketsDTO getTicketById(Integer id) {
        Tickets ticket = iR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        // Mapear entidad a DTO
        return modelMapper.map(ticket, TicketsDTO.class);
    }

    @Override
    public Tickets listarId(int id) {
        return iR.findById(id).orElse(new Tickets());
    }


}
