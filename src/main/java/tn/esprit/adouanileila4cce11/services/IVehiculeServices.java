package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Vehicule;

import java.util.List;

public interface IVehiculeServices {
    Vehicule create (Vehicule vehicule);

    Vehicule update(Vehicule vehicule);

    Vehicule findById(Vehicule vehicule);
    void deleteById(Long id);
    Vehicule findById(Long id);
    List<Vehicule> findAll();
}
