package pe.edu.upc.calpabackend.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendancesByYearDTO {
    private String vendedor;
    private Double anio;
    private Double mes;
    private int total_asistencias;
}
