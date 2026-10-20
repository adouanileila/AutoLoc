
package tn.esprit.adouanileila4cce11.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.adouanileila4cce11.domain.Agence;
import tn.esprit.adouanileila4cce11.services.IAgenceServices;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceRestController {

    private final IAgenceServices agenceServices;

    @PostMapping("/add")
    public Agence add(@RequestBody Agence agence) {
        return agenceServices.create(agence);
    }

    @GetMapping("/get/{idAgence}")
    public Agence get(@PathVariable Long idAgence) {
        return agenceServices.findById(idAgence);
    }

    @GetMapping("/all")
    public List<Agence> getAll() {
        return agenceServices.findAll();
    }

    @PutMapping("/update/{idAgence}")
    public Agence update(
            @PathVariable Long idAgence,
            @RequestBody Agence agence) {
        return agenceServices.update(idAgence, agence);
    }

    @DeleteMapping("/delete/{idAgence}")
    public void delete(@PathVariable Long idAgence) {
        agenceServices.deleteById(idAgence);
    }
}
