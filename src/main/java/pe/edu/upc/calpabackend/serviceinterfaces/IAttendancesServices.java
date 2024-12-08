package pe.edu.upc.calpabackend.serviceinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.calpabackend.entities.Attendances;

import java.util.List;

public interface IAttendancesServices {
    public void insert(Attendances attendances);
    public List<Attendances> list();
    public void delete(int id);
    public void update(Attendances attendances);
    public Attendances listarId(int id);
    public List<String[]> getAttendancesByYear(@Param("anio") int anio);

}
