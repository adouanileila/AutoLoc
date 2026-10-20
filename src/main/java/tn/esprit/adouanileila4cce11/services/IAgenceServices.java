
package tn.esprit.adouanileila4cce11.services;

import tn.esprit.adouanileila4cce11.domain.Agence;
import java.util.List;

public interface IAgenceServices {

    Agence create(Agence agence);

    Agence update(Long id ,Agence agence);

    Agence findById(Long id);

    List<Agence> findAll();

    void deleteById(Long id);
}
