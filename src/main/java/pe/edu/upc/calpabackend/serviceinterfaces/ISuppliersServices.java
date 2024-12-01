package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Roles;
import pe.edu.upc.calpabackend.entities.Suppliers;

import java.util.List;

public interface ISuppliersServices {
    Suppliers insert(Suppliers suppliers);
    public List<Suppliers> list();
    public void delete(int id);
    public void update(Suppliers suppliers);
    public Suppliers listarId(int id);

}
