
package tn.esprit.adouanileila4cce11.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.adouanileila4cce11.domain.Vehicule;
import tn.esprit.adouanileila4cce11.services.IVehiculeServices;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeServices vehiculeServices;

    @PostMapping("/add")
    public Vehicule add(@RequestBody Vehicule vehicule) {
        return vehiculeServices.create(vehicule);
    }

    @GetMapping("/get/{idVehicule}")
    public Vehicule get(@PathVariable Long idVehicule) {
        return vehiculeServices.findById(idVehicule);
    }
    @GetMapping("/all")
    public List<Vehicule> getAll() { return vehiculeServices.findAll();
    }
    @PutMapping("/update/{idVehicule}")
    public Vehicule update(@PathVariable Long idVehicule, @RequestBody Vehicule vehicule) {
        return vehiculeServices.update(idVehicule, vehicule);
    }
    @DeleteMapping("/delete/{idVehicule}")
    public void delete(@PathVariable Long idVehicule) { vehiculeServices.deleteById(idVehicule); }
}
