package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.entities.Roles;

import java.util.List;

public interface IRolesServices {
    public void insert(Roles roles);
    public List<Roles> list();
    public void delete(Long id);
    public void update(Roles roles);
    public Roles listarId(Long id);

}
