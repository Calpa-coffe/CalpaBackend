package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.repositories.ITicketsRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.ITicketsServices;

import java.util.List;

@Service
public class TicketsServicesImplements implements ITicketsServices {
    @Autowired
    private ITicketsRepository iR;

    @Override
    public Tickets insert(Tickets tickets) {
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
}
