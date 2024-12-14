package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.CategoryProduct;

@Repository
public interface ICategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
}
