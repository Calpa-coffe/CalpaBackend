package pe.edu.upc.calpabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.calpabackend.entities.Attendances;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAttendancesRepository extends JpaRepository<Attendances, Integer> {

    @Query(value = "SELECT " +
            "ut.username AS vendedor, " +
            "DATE_PART('year', att.dateStarting) AS anio, " +
            "DATE_PART('month', att.dateStarting) AS mes, " +
            "COUNT(att.attendance) AS total_asistencias " +
            "FROM " +
            "attendances AS att " +
            "INNER JOIN " +
            "user_table AS ut " +
            "ON " +
            "att.user_id = ut.id " +
            "WHERE " +
            "att.attendance = TRUE AND " +
            "att.dateStarting >= :startDate AND " +
            "att.dateStarting < :endDate " +
            "GROUP BY " +
            "ut.username, DATE_PART('year', att.dateStarting), DATE_PART('month', att.dateStarting) " +
            "ORDER BY " +
            "ut.username, anio, mes ",
            nativeQuery = true)
    public List<String[]> getAttendancesByYear(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );



}
