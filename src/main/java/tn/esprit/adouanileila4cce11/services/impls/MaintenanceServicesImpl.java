package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Maintenance;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IMaintenanceRepository;
import tn.esprit.adouanileila4cce11.services.IMaintenanceServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServicesImpl implements IMaintenanceServices {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance create(Maintenance maintenance) {
        validate(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance update(Maintenance maintenance) {
        validate(maintenance);
        findById(maintenance.getIdMaintenance());
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance introuvable : " + id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        maintenanceRepository.delete(findById(id));
    }

    private void validate(Maintenance maintenance) {
        if (maintenance == null) {
            throw new IllegalArgumentException("La maintenance est obligatoire.");
        }
        if (maintenance.getDateDebut() == null) {
            throw new IllegalArgumentException("La date de début est obligatoire.");
        }
        if (maintenance.getDateFin() != null
                && maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas précéder la date de début.");
        }
        if (maintenance.getDescription() == null
                || maintenance.getDescription().isBlank()) {
            throw new IllegalArgumentException("La description est obligatoire.");
        }
    }
}