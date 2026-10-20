package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Vehicule;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IVehiculeRepository;
import tn.esprit.adouanileila4cce11.services.IVehiculeServices;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServicesImpl implements IVehiculeServices {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        validateVehicule(vehicule);
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau véhicule ne doit pas avoir d'identifiant."
            );
        }
        return vehiculeRepository.save(vehicule);
    }
    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        validateVehicule(vehicule);

        if (id == null) {
            throw new IllegalArgumentException(
                    "L'identifiant du véhicule est obligatoire."
            );
        }

        // Vérifier que le véhicule existe
        Vehicule existingVehicule = findById(id);

        // Mettre à jour les informations du véhicule existant
        vehicule.setIdVehicule(existingVehicule.getIdVehicule());

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Vehicule vehicule) {
        if (vehicule == null || vehicule.getIdVehicule() == null) {
            throw new IllegalArgumentException("Le véhicule ou son identifiant est null.");
        }
        return findById(vehicule.getIdVehicule());
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Véhicule introuvable : " + id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Vehicule vehicule = findById(id);
        vehiculeRepository.delete(vehicule);
    }

    private void validateVehicule(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule est obligatoire.");
        }

        if (vehicule.getImmatriculation() == null
                || vehicule.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException(
                    "L'immatriculation est obligatoire."
            );
        }

        if (vehicule.getMarque() == null || vehicule.getMarque().isBlank()) {
            throw new IllegalArgumentException("La marque est obligatoire.");
        }

        if (vehicule.getModele() == null || vehicule.getModele().isBlank()) {
            throw new IllegalArgumentException("Le modèle est obligatoire.");
        }

        if (vehicule.getTarifJournalier() == null
                || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Le tarif journalier doit être positif ou nul."
            );
        }

        if (vehicule.getCategorie() == null) {
            throw new IllegalArgumentException("La catégorie est obligatoire.");
        }

        if (vehicule.getStatut() == null) {
            throw new IllegalArgumentException("Le statut est obligatoire.");
        }
    }
}