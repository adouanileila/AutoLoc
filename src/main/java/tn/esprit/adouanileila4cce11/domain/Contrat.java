package tn.esprit.adouanileila4cce11.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    private BigDecimal montantTotal;

    private boolean valide;

    // Association Contrat - Reservation : OneToOne
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation", unique = true)
    @ToString.Exclude
    private Reservation reservation;

    // Association Contrat - Paiement : OneToMany
    @Builder.Default
    @OneToMany(
            mappedBy = "contrat",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Paiement> paiements = new ArrayList<>();
}