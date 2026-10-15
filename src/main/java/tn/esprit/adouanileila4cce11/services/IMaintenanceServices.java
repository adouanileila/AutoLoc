package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Maintenance;
import java.util.List;

public interface IMaintenanceServices {
    Maintenance create(Maintenance maintenance);
    Maintenance update(Maintenance maintenance);
    Maintenance findById(Long id);
    List<Maintenance> findAll();
    void deleteById(Long id);
}