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


    @Query(value = "select pr.nameproduct, sum(quantity) as cantidad_total from ticket_products as tp \n" +
            "inner join products as pr on tp.product_id = pr.id \n" +
            "inner join categoryproduct as catp on pr.categoryproduct_id = catp.id \n" +
            "inner join tickets as tc on tp.ticket_id = tc.id \n" +
            "where catp.category = :categoryname \n" +
            "and tc.datepay BETWEEN :startDate AND :endDate \n" +
            "group by pr.nameproduct \n" +
            "order by cantidad_total desc ", nativeQuery = true)
    public List<String[]> getquantitypercategory(@Param("categoryname") String categoryname,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);


    @Query(value = "select pr.nameproduct, sum(quantity) as cantidad_total from ticket_products as tp \n" +
            "inner join products as pr on tp.product_id = pr.id \n" +
            "inner join categoryproduct as catp on pr.categoryproduct_id = catp.id \n" +
            "inner join tickets as tc on tp.ticket_id = tc.id \n" +
            "where catp.category = :categoryname \n" +
            "and tc.datepay BETWEEN :startDate AND :endDate \n" +
            "group by pr.nameproduct \n" +
            "order by cantidad_total desc \n" +
            "limit 1 ", nativeQuery = true)
    public List<String[]> getmostproductsellcat(@Param("categoryname") String categoryname,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);


}
