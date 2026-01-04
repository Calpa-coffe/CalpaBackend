package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "detailsuppliers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailSuppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description", nullable = false, length = 254)
    private String description;
    @Column(name = "totalprice", nullable = false, length = 254)
    private Double totalprice;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Suppliers supplier;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
}
