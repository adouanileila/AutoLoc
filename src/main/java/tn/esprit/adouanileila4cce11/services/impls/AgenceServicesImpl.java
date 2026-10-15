package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Agence;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IAgenceRepository;
import tn.esprit.adouanileila4cce11.services.IAgenceServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServicesImpl implements IAgenceServices {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence agence) {
        validate(agence);
        return agenceRepository.save(agence);
    }

    @Override
    public Agence update(Agence agence) {
        validate(agence);
        findById(agence.getIdAgence());
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Agence introuvable : " + id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        agenceRepository.delete(findById(id));
    }

    private void validate(Agence agence) {
        if (agence == null) {
            throw new IllegalArgumentException("L'agence est obligatoire.");
        }
        if (agence.getVille() == null || agence.getVille().isBlank()) {
            throw new IllegalArgumentException("La ville est obligatoire.");
        }
        if (agence.getAdresse() == null || agence.getAdresse().isBlank()) {
            throw new IllegalArgumentException("L'adresse est obligatoire.");
        }
    }
}