package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Bookings;

import java.util.List;

public interface IBookingsServices {
    Bookings insert(Bookings bookings);
    public List<Bookings> list();
    public void delete(int id);
    public void update(Bookings bookings);
}
