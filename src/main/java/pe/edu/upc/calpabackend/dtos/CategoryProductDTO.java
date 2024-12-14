package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryProductDTO {
    private int id;
    private String nameproduct;
}
