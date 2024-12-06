package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "document", nullable = false, length = 254)
    private String document;
    @Column(name = "clientname", nullable = false, length = 254)
    private String clientname;
    @Column(name = "phoneclient", nullable = false, length = 500)
    private String phoneclient;
    @Column(name = "email", nullable = false, length = 200)
    private String email;
    @Column(name = "birthday", nullable = true, length = 254)
    private LocalDate birthday;
}
