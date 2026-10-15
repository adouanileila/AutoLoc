package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Paiement;
import java.util.List;

public interface IPaiementServices {
    Paiement findById(Long id);
    List<Paiement> findAll();
}