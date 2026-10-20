
package tn.esprit.adouanileila4cce11.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    private String immatriculation;

    private String marque;

    private String modele;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    // Plusieurs véhicules peuvent appartenir à une même agence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agence")
    @ToString.Exclude
    @JsonBackReference("agence-vehicules")
    private Agence agence;

    // Un véhicule peut avoir plusieurs maintenances
    @Builder.Default
    @OneToMany(
            mappedBy = "vehicule",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Maintenance> maintenances = new ArrayList<>();

    // Association ManyToMany avec Equipement
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    @ToString.Exclude
    private Set<Equipement> equipements = new HashSet<>();

    // Un véhicule peut être lié à plusieurs réservations
    @Builder.Default
    @OneToMany(
            mappedBy = "vehicule",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();
}
