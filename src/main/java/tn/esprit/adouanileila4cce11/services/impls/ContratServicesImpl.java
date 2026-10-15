package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Contrat;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IContratRepository;
import tn.esprit.adouanileila4cce11.services.IContratServices;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServicesImpl implements IContratServices {

    private final IContratRepository contratRepository;

    @Override
    public Contrat create(Contrat contrat) {
        validateContrat(contrat);

        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant."
            );
        }

        return contratRepository.save(contrat);
    }

    @Override
    public Contrat update(Contrat contrat) {
        validateContrat(contrat);

        if (contrat.getIdContrat() == null) {
            throw new IllegalArgumentException(
                    "L'identifiant du contrat est obligatoire."
            );
        }

        Contrat contratExistant = findById(contrat.getIdContrat());

        contratExistant.setDateSignature(contrat.getDateSignature());
        contratExistant.setMontantTotal(contrat.getMontantTotal());
        contratExistant.setValide(contrat.isValide());
        contratExistant.setReservation(contrat.getReservation());

        return contratRepository.save(contratExistant);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contrat introuvable : " + id
                ));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Contrat contrat = findById(id);
        contratRepository.delete(contrat);
    }

    private void validateContrat(Contrat contrat) {
        if (contrat == null) {
            throw new IllegalArgumentException(
                    "Le contrat est obligatoire."
            );
        }

        if (contrat.getMontantTotal() == null) {
            throw new IllegalArgumentException(
                    "Le montant total du contrat est obligatoire."
            );
        }

        if (contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Le montant total du contrat ne peut pas être négatif."
            );
        }

        if (contrat.getDateSignature() == null) {
            throw new IllegalArgumentException(
                    "La date de signature est obligatoire."
            );
        }
    }
}