
package tn.esprit.adouanileila4cce11.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.adouanileila4cce11.domain.Vehicule;
import tn.esprit.adouanileila4cce11.services.IVehiculeServices;

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
}
