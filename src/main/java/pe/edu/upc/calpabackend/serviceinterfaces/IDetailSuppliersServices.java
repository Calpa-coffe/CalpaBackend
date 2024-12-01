package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.DetailSuppliers;

import java.util.List;

public interface IDetailSuppliersServices {
    DetailSuppliers insert(DetailSuppliers detailSuppliers);
    public List<DetailSuppliers> list();
    public void delete(int id);
    public void update(DetailSuppliers detailSuppliers);
}
