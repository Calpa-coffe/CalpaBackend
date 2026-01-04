package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "namesupplier", nullable = false, length = 40)
    private String namesupplier;
    @Column(name = "phonesupplier", nullable = false, length = 40)
    private String phonesupplier;
    @Column(name = "email", nullable = false, length = 40)
    private String email;

}
