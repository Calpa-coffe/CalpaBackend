package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.Products;
import pe.edu.upc.calpabackend.repositories.IProductsRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.IProductServices;

import java.util.List;

@Service
public class ProductsServicesImplements implements IProductServices {
    @Autowired
    private IProductsRepository pR;

    @Override
    public Products insert(Products product) {
        return pR.save(product);
    }

    @Override
    public List<Products> list() {
        return pR.findAll();
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }

    @Override
    public void update(Products product) {
        pR.save(product);
    }
}
