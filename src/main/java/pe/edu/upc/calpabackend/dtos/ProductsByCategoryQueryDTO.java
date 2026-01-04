package pe.edu.upc.calpabackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductsByCategoryQueryDTO {
    private int id;
    private String nameproduct;
    private String description;
    private String image;
    private Double price;
}
