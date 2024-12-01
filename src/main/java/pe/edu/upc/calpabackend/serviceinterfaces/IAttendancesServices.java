package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Attendances;

import java.util.List;

public interface IAttendancesServices {
    Attendances insert(Attendances attendances);
    public List<Attendances> list();
    public void delete(int id);
    public void update(Attendances attendances);
}
