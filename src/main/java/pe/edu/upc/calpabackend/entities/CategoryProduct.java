package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoryproduct")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "category", nullable = false, length = 254)
    private String nameproduct;
}
