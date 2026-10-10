
package tn.esprit.adouanileila4cce11.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAgence;

    private String ville;

    private String adresse;

    private String telephone;

    // Une agence peut posséder plusieurs véhicules
    @Builder.Default
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Vehicule> vehicules = new ArrayList<>();

    // Une agence peut avoir plusieurs employés
    @Builder.Default
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Employe> employes = new ArrayList<>();
}
