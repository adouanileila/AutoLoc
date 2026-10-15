package tn.esprit.adouanileila4cce11.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.adouanileila4cce11.domain.Reservation;
import tn.esprit.adouanileila4cce11.exception.ResourceNotFoundException;
import tn.esprit.adouanileila4cce11.repository.IReservationRepository;
import tn.esprit.adouanileila4cce11.services.IReservationServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServicesImpl implements IReservationServices {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation reservation) {
        validateReservation(reservation);

        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle réservation ne doit pas avoir d'identifiant."
            );
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        validateReservation(reservation);

        if (reservation.getIdReservation() == null) {
            throw new IllegalArgumentException(
                    "L'identifiant de la réservation est obligatoire."
            );
        }

        Reservation reservationExistante =
                findById(reservation.getIdReservation());

        reservationExistante.setDateDebut(reservation.getDateDebut());
        reservationExistante.setDateFin(reservation.getDateFin());
        reservationExistante.setStatut(reservation.getStatut());
        reservationExistante.setClient(reservation.getClient());
        reservationExistante.setVehicule(reservation.getVehicule());

        return reservationRepository.save(reservationExistante);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Réservation introuvable : " + id
                ));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = findById(id);
        reservationRepository.delete(reservation);
    }

    private void validateReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException(
                    "La réservation est obligatoire."
            );
        }

        if (reservation.getDateDebut() == null) {
            throw new IllegalArgumentException(
                    "La date de début est obligatoire."
            );
        }

        if (reservation.getDateFin() == null) {
            throw new IllegalArgumentException(
                    "La date de fin est obligatoire."
            );
        }

        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début."
            );
        }

        if (reservation.getStatut() == null) {
            throw new IllegalArgumentException(
                    "Le statut de la réservation est obligatoire."
            );
        }
    }
}