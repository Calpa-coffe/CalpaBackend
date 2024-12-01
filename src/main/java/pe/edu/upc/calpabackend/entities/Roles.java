package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "rol"})})
@Getter
@Setter
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rol", nullable = false, length = 35)
    private String rol;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
