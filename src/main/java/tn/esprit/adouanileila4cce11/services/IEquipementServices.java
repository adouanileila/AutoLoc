package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Equipement;
import java.util.List;

public interface IEquipementServices {
    Equipement create(Equipement equipement);
    Equipement update(Equipement equipement);
    Equipement findById(Long id);
    List<Equipement> findAll();
    void deleteById(Long id);
}