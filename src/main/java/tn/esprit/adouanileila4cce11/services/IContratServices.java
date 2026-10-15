package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Contrat;
import java.util.List;

public interface IContratServices {
    Contrat create(Contrat contrat);
    Contrat update(Contrat contrat);
    Contrat findById(Long id);
    List<Contrat> findAll();
    void deleteById(Long id);
}