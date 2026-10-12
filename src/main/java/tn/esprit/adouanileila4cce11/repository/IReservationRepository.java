package tn.esprit.adouanileila4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.adouanileila4cce11.domain.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}