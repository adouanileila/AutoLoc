package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Client;
import java.util.List;

public interface IClientServices {
    Client create(Client client);
    Client update(Client client);
    Client findById(Long id);
    List<Client> findAll();
    void deleteById(Long id);
}