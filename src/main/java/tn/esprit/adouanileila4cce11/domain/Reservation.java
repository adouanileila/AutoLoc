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

    @ManyToOne
    @JoinColumn(name = "id_client")
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    @ToString.Exclude
    private Vehicule vehicule;

    @OneToOne
    @JoinColumn(name = "id_contrat")
    @ToString.Exclude
    private Contrat contrat;
}