package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.Members;

@Getter
@Setter
public class BookingsDTO {
    private int id;
    private String datebooking;
    private Members member;
}
