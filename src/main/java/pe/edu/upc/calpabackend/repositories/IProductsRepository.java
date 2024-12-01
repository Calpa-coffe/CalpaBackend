package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.Products;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Integer> {
}
