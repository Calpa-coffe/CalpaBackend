package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.Tickets;

import java.util.Optional;

@Repository
public interface ITicketsRepository extends JpaRepository<Tickets, Integer> {

    Optional<Tickets> findById(Integer id);
}
