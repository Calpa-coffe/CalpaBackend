package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.entities.TypePayments;

import java.util.List;

public interface ITypePaymentServices {
    public void insert(TypePayments typePayments);
    public List<TypePayments> list();
    public void delete(int id);
    public void update(TypePayments typePayments);
    public TypePayments listarId(int id);

}
