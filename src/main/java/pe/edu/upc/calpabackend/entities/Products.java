package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nameproduct", nullable = false, length = 254)
    private String nameproduct;
    @Column(name = "description", nullable = false, length = 254)
    private String description;
    @Column(name = "image", nullable = false, length = 500)
    private String image;
    @Column(name = "price", nullable = false, length = 10)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoryproduct_id", nullable = false)
    private CategoryProduct categoryProduct;

}
