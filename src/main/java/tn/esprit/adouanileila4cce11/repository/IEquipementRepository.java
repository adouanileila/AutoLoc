package tn.esprit.adouanileila4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.adouanileila4cce11.domain.Equipement;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}