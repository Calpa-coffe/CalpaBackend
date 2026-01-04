package pe.edu.upc.calpabackend.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.calpabackend.entities.CategoryProduct;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String nameproduct;
    private String description;
    private String image;
    private Double price;
    private CategoryProduct categoryProduct;
}
