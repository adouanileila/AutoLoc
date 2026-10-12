package tn.esprit.adouanileila4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.adouanileila4cce11.domain.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
}