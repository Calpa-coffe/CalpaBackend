package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.Suppliers;

@Repository
public interface ISuppliersRepository extends JpaRepository<Suppliers, Integer> {
}
