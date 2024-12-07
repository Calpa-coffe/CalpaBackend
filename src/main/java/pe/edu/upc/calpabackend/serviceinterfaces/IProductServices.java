package pe.edu.upc.calpabackend.serviceinterfaces;

import pe.edu.upc.calpabackend.entities.Members;
import pe.edu.upc.calpabackend.entities.Products;

import java.util.List;

public interface IProductServices {
    public void insert(Products product);
    public List<Products> list();
    public void delete(int id);
    public void update(Products product);
    public Products listarId(int id);

}
