package pe.edu.upc.calpabackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "clientname", nullable = true, length = 254)
    private String clientname;
    @Column(name = "datepay", nullable = false, length = 254)
    private LocalDate datepay;
    @Column(name = "total", nullable = false, length = 254)
    private Double total;
    @Column(name = "amount", nullable = false, length = 254)
    private Double amountpayment;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true)
    private Members members;

    @ManyToOne
    @JoinColumn(name = "typepay_id", nullable = false)
    private TypePayments typepayments;

}
