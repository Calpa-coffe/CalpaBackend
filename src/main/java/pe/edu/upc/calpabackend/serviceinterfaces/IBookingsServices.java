package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Attendances;
import pe.edu.upc.calpabackend.entities.Bookings;

import java.util.List;

public interface IBookingsServices {
    public void insert(Bookings bookings);
    public List<Bookings> list();
    public void delete(int id);
    public void update(Bookings bookings);
    public Bookings listarId(int id);

}
