package pe.edu.upc.calpabackend.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.TicketProduct;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.exception.ResourceNotFoundException;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;
import pe.edu.upc.calpabackend.repositories.ITicketsRepository;
import pe.edu.upc.calpabackend.repositories.ITypePaymentsRepository;
import pe.edu.upc.calpabackend.repositories.IProductsRepository;
import pe.edu.upc.calpabackend.repositories.ITicketProductRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.ITicketsServices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TicketsServicesImplements implements ITicketsServices {
    private static final Logger logger = LoggerFactory.getLogger(TicketsServicesImplements.class);

    @Autowired
    private ITicketsRepository iR;

    @Autowired
    private ITypePaymentsRepository typePaymentsRepository;

    @Autowired
    private IProductsRepository productsRepository;

    @Autowired
    private ITicketProductRepository ticketProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Tickets insert(Tickets tickets) {
        logger.info("Recibiendo ticket para insertar: " + tickets);

        if (tickets.getTypepayments() == null || tickets.getTypepayments().getId() == 0) {
            throw new ResourceNotFoundException("Tipo de pago no proporcionado o inválido.");
        }

        TypePayments tp = typePaymentsRepository.findById(tickets.getTypepayments().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de pago no encontrado con id: " + tickets.getTypepayments().getId()));
        tickets.setTypepayments(tp);

        double total = 0.0;
        if (tickets.getTicketProducts() != null) {
            for (TicketProduct tpu : tickets.getTicketProducts()) {
                Products product = productsRepository.findById(tpu.getProduct().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + tpu.getProduct().getId()));
                tpu.setProduct(product);
                tpu.setTicket(tickets);
                total += product.getPrice() * tpu.getQuantity();
            }
        } else {
            throw new IllegalArgumentException("No se proporcionaron productos para el ticket.");
        }

        tickets.setTotal(total);
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
        // Verificar y asignar TypePayments
        if (tickets.getTypepayments() != null && tickets.getTypepayments().getId() != 0) {
            TypePayments tp = typePaymentsRepository.findById(tickets.getTypepayments().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de pago no encontrado con id: " + tickets.getTypepayments().getId()));
            tickets.setTypepayments(tp);
        }

        // Calcular total
        double total = 0.0;
        if (tickets.getTicketProducts() != null) {
            for (TicketProduct tp : tickets.getTicketProducts()) {
                Products product = productsRepository.findById(tp.getProduct().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + tp.getProduct().getId()));
                tp.setProduct(product);
                tp.setTicket(tickets);
                total += product.getPrice() * tp.getQuantity();
            }
        }
        tickets.setTotal(total);

        iR.save(tickets);
    }

    @Override
    public TicketsDTO getTicketById(Integer id) {
        Tickets ticket = iR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        return modelMapper.map(ticket, TicketsDTO.class);
    }

    @Override
    public Tickets listarId(int id) {
        return iR.findById(id).orElse(new Tickets());
    }
}
