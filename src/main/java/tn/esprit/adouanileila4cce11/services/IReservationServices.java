package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Reservation;
import java.util.List;

public interface IReservationServices {
    Reservation create(Reservation reservation);
    Reservation update(Reservation reservation);
    Reservation findById(Long id);
    List<Reservation> findAll();
    void deleteById(Long id);
}