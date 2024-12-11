package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.Tickets;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITicketsRepository extends JpaRepository<Tickets, Integer> {

    Optional<Tickets> findById(Integer id);
    @Query(value = "SELECT t.total, t.datepay\n" +
            "FROM tickets t\n" +
            "JOIN user_table u ON t.user_id = u.id\n" +
            "WHERE t.datepay = :findDate\n" +
            "  AND u.username = :username;",nativeQuery = true)
    public List<String[]> getTicketsByDatepay(
            @Param("findDate") LocalDate findDate,
            @Param("username") String username);

}
