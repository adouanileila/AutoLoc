package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Equipement;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IEquipementRepository;
import tn.esprit.adouanileila4cce11.services.IEquipementServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServicesImpl implements IEquipementServices {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement create(Equipement equipement) {
        validate(equipement);
        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null à la création.");
        }
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement update(Equipement equipement) {
        validate(equipement);
        if (equipement.getIdEquipement() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire.");
        }
        findById(equipement.getIdEquipement());
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement findById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Équipement introuvable : " + id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        equipementRepository.delete(findById(id));
    }

    private void validate(Equipement equipement) {
        if (equipement == null || equipement.getLibelle() == null
                || equipement.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Le libellé est obligatoire.");
        }
    }
}