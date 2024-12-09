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
        // Verificar si users es nulo
        if (tickets.getUsers() == null) {
            System.out.println("Se recibió parámetro nulo de user.");
            throw new ResourceNotFoundException("Usuario no proporcionado o inválido");
        } else {
            System.out.println("Parámetro recibido, userID: " + tickets.getUsers().getId());
            if (tickets.getUsers().getId() <= 0) {
                throw new ResourceNotFoundException("Usuario no proporcionado o inválido");
            }
        }

        // Verificar el tipo de pago
        if (tickets.getTypepayments() == null || tickets.getTypepayments().getId() <= 0) {
            System.out.println("Tipo de pago recibido: " + tickets.getTypepayments());
            throw new ResourceNotFoundException("Tipo de pago no proporcionado o inválido");
        }

        // Buscar el objeto TypePayments desde la base de datos
        TypePayments typePayment = iT.findById(tickets.getTypepayments().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de pago no encontrado"));

        // Asociar el objeto TypePayments con el ticket
        tickets.setTypepayments(typePayment);

        // Calcular el total en base a productos y cantidad
        double total = 0.0;
        if (tickets.getProduct() != null) {
            for (Products product : tickets.getProduct()) {
                total += product.getPrice() * tickets.getQuantity();
            }
        }
        tickets.setTotal(total);

        // Guardar el ticket y devolverlo
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
