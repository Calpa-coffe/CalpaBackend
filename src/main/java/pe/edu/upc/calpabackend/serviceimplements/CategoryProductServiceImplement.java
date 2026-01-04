package pe.edu.upc.calpabackend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.calpabackend.entities.CategoryProduct;
import pe.edu.upc.calpabackend.entities.DetailSuppliers;
import pe.edu.upc.calpabackend.repositories.ICategoryProductRepository;
import pe.edu.upc.calpabackend.serviceinterfaces.ICategoryProductService;

import java.util.List;

@Service
public class CategoryProductServiceImplement implements ICategoryProductService {
    @Autowired
    private ICategoryProductRepository cR;


    @Override
    public void insert(CategoryProduct categoryProduct) {
        cR.save(categoryProduct);
    }

    @Override
    public List<CategoryProduct> list() {
        return cR.findAll();
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public void update(CategoryProduct categoryProduct) {
        cR.save(categoryProduct);
    }

    @Override
    public CategoryProduct listarId(int id) {
        return cR.findById(id).orElse(new CategoryProduct());
    }
}
