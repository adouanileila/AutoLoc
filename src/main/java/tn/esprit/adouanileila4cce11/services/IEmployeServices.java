package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Employe;
import java.util.List;

public interface IEmployeServices {
    Employe create(Employe employe);
    Employe update(Employe employe);
    Employe findById(Long id);
    List<Employe> findAll();
    void deleteById(Long id);
}