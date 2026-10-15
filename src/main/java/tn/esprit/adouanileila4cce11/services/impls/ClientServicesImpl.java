package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Client;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IClientRepository;
import tn.esprit.adouanileila4cce11.services.IClientServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServicesImpl implements IClientServices {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        validate(client);
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null à la création.");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        validate(client);
        if (client.getIdClient() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire.");
        }
        findById(client.getIdClient());
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client introuvable : " + id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.delete(findById(id));
    }

    private void validate(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Le client est obligatoire.");
        }
        if (client.getNom() == null || client.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire.");
        }
        if (client.getPrenom() == null || client.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire.");
        }
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email est obligatoire.");
        }
        if (client.getNumPermis() == null || client.getNumPermis().isBlank()) {
            throw new IllegalArgumentException("Le numéro de permis est obligatoire.");
        }
    }
}