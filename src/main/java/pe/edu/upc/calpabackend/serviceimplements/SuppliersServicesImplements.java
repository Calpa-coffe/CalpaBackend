package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Roles;
import pe.edu.upc.calpabackend.entities.Suppliers;
import pe.edu.upc.calpabackend.repositories.IDetailSuppliersRepository;
import pe.edu.upc.calpabackend.repositories.ISuppliersRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.ISuppliersServices;

import java.util.List;

@Service
public class SuppliersServicesImplements implements ISuppliersServices {
    @Autowired
    private ISuppliersRepository sR;

    @Override
    public void insert(Suppliers suppliers) {
         sR.save(suppliers);
    }

    @Override
    public List<Suppliers> list() {
        return sR.findAll();
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }

    @Override
    public void update(Suppliers suppliers) {
        sR.save(suppliers);
    }

    @Override
    public Suppliers listarId(int id) {
        return sR.findById(id).orElse(new Suppliers());
    }
}
