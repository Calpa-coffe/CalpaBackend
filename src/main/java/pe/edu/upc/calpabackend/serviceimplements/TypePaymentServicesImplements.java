package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Tickets;
import pe.edu.upc.calpabackend.entities.TypePayments;
import pe.edu.upc.calpabackend.repositories.ITypePaymentsRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.ITypePaymentServices;

import java.util.List;

@Service
public class TypePaymentServicesImplements implements ITypePaymentServices {
    @Autowired
    private ITypePaymentsRepository tR;

    @Override
    public void insert(TypePayments typePayments) {
        tR.save(typePayments);
    }

    @Override
    public List<TypePayments> list() {
        return tR.findAll();
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public void update(TypePayments typePayments) {
        tR.save(typePayments);
    }

    @Override
    public TypePayments listarId(int id) {
        return tR.findById(id).orElse(new TypePayments());
    }
}
