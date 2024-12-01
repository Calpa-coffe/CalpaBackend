package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "attendance", nullable = false, length = 254)
    private Boolean attendance;
    @Column(name = "datestarting", nullable = false, length = 254)
    private LocalDateTime datestarting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
}
