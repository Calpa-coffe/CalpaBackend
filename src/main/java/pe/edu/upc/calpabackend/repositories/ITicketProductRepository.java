package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.TicketProduct;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITicketProductRepository extends JpaRepository<TicketProduct, Integer> {
}
