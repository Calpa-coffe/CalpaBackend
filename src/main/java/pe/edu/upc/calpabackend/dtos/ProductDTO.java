package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String nameproduct;
    private String description;
    private String image;
    private Double price;
}
