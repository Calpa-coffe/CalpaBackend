package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Attendances;
import pe.edu.upc.calpabackend.repositories.IAttendancesRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IAttendancesServices;

import java.util.List;

@Service
public class AttendanceServicesImplements implements IAttendancesServices {

    @Autowired
    private IAttendancesRepository aR;

    @Override
    public Attendances insert(Attendances attendances) {
        return aR.save(attendances);
    }

    @Override
    public List<Attendances> list() {
        return aR.findAll();
    }

    @Override
    public void delete(int id) {
        aR.deleteById(id);
    }

    @Override
    public void update(Attendances attendances) {
        aR.save(attendances);
    }
}
