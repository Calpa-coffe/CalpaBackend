package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.Roles;
import pe.edu.upc.calpabackend.repositories.IRolesRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IRolesServices;

import java.util.List;

@Service
public class RolesServicesImplements implements IRolesServices {

    @Autowired
    private IRolesRepository rR;

    @Override
    public Roles insert(Roles roles) {
        return rR.save(roles);
    }

    @Override
    public List<Roles> list() {
        return rR.findAll();
    }

    @Override
    public void delete(Long id) {
        rR.deleteById(id);
    }

    @Override
    public void update(Roles roles) {
        rR.save(roles);
    }

    @Override
    public Roles listarId(Long id) {
        return rR.findById(id).orElse(new Roles());
    }
}
