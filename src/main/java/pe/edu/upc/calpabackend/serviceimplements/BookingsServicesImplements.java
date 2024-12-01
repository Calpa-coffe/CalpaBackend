package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Bookings;
import pe.edu.upc.calpabackend.repositories.IBookingsRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IBookingsServices;

import java.util.List;

@Service
public class BookingsServicesImplements implements IBookingsServices {
    @Autowired
    private IBookingsRepository bR;

    @Override
    public Bookings insert(Bookings bookings) {
        return bR.save(bookings);
    }

    @Override
    public List<Bookings> list() {
        return bR.findAll();
    }

    @Override
    public void delete(int id) {
        bR.deleteById(id);
    }

    @Override
    public void update(Bookings bookings) {
        bR.save(bookings);
    }

    @Override
    public Bookings listarId(int id) {
        return bR.findById(id).orElse(new Bookings());
    }
}
