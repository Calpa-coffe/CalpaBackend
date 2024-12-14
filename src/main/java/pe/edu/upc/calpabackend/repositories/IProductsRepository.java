package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.Products;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Integer> {
    @Query(value = "SELECT p.nameproduct, p.description, p.image, p.price FROM products p\n" +
            "JOIN categoryproduct c ON p.categoryproduct_id = c.id WHERE c.category = :typecategory;", nativeQuery = true)
    public List<String[]> getProductsByCategoryProduct (@Param("typecategory") String typecategory);
}
