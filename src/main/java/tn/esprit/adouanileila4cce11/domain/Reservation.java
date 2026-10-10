package tn.esprit.adouanileila4cce11.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    // Plusieurs réservations peuvent appartenir au même client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    @ToString.Exclude
    private Client client;

    // Plusieurs réservations peuvent concerner le même véhicule
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule")
    @ToString.Exclude
    private Vehicule vehicule;

    // Une réservation peut être associée à un seul contrat
    @OneToOne(
            mappedBy = "reservation",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private Contrat contrat;
}