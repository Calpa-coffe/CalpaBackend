package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Bookings;
import pe.edu.upc.calpabackend.entities.DetailSuppliers;
import pe.edu.upc.calpabackend.repositories.IDetailSuppliersRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IDetailSuppliersServices;

import java.util.List;

@Service
public class DetailSuppliersServicesImplements implements IDetailSuppliersServices {
    @Autowired
    private IDetailSuppliersRepository dR;

    @Override
    public DetailSuppliers insert(DetailSuppliers detailSuppliers) {
        return dR.save(detailSuppliers);
    }

    @Override
    public List<DetailSuppliers> list() {
        return dR.findAll();
    }

    @Override
    public void delete(int id) {
        dR.deleteById(id);
    }

    @Override
    public void update(DetailSuppliers detailSuppliers) {
        dR.save(detailSuppliers);
    }

    @Override
    public DetailSuppliers listarId(int id) {
        return dR.findById(id).orElse(new DetailSuppliers());
    }
}
