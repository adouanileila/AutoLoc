package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Paiement;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IPaiementRepository;
import tn.esprit.adouanileila4cce11.services.IPaiementServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServicesImpl implements IPaiementServices {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paiement introuvable : " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
}