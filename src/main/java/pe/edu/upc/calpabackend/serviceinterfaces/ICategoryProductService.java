package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.CategoryProduct;
import pe.edu.upc.calpabackend.entities.Products;

import java.util.List;

public interface ICategoryProductService {
    public void insert(CategoryProduct categoryProduct);
    public List<CategoryProduct> list();
    public void delete(int id);
    public void update(CategoryProduct categoryProduct);
    public CategoryProduct listarId(int id);
}
