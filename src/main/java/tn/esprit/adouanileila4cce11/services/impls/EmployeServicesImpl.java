package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Employe;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IEmployeRepository;
import tn.esprit.adouanileila4cce11.services.IEmployeServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServicesImpl implements IEmployeServices {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe create(Employe employe) {
        validateEmploye(employe);

        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel employé ne doit pas avoir d'identifiant."
            );
        }

        return employeRepository.save(employe);
    }

    @Override
    public Employe update(Employe employe) {
        validateEmploye(employe);

        if (employe.getIdEmploye() == null) {
            throw new IllegalArgumentException(
                    "L'identifiant de l'employé est obligatoire."
            );
        }

        Employe employeExistant = findById(employe.getIdEmploye());

        employeExistant.setNom(employe.getNom());
        employeExistant.setPrenom(employe.getPrenom());
        employeExistant.setRole(employe.getRole());
        employeExistant.setAgence(employe.getAgence());

        return employeRepository.save(employeExistant);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employé introuvable : " + id
                ));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Employe employe = findById(id);
        employeRepository.delete(employe);
    }

    private void validateEmploye(Employe employe) {
        if (employe == null) {
            throw new IllegalArgumentException(
                    "L'employé est obligatoire."
            );
        }

        if (employe.getNom() == null || employe.getNom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom de l'employé est obligatoire."
            );
        }

        if (employe.getPrenom() == null || employe.getPrenom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le prénom de l'employé est obligatoire."
            );
        }

        if (employe.getRole() == null) {
            throw new IllegalArgumentException(
                    "Le rôle de l'employé est obligatoire."
            );
        }
    }
}