package epitech.backend.web.compte.controller;

import epitech.backend.web.cart.representation.CartRepresentation;
import epitech.backend.web.compte.representation.CompteRepresentation;
import epitech.backend.web.compte.service.WebCompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/compte")
public class CompteController {
    private WebCompteService webCompteService;

    @Inject
    public CompteController(WebCompteService webCompteService) {
        this.webCompteService = webCompteService;
    }


    /**
     * Méthode de récupération d'un compte
     *
     * @param idCompte : id du compte à récupérer
     * @return Le compte demandé
     */

    @GetMapping("/{idCompte}")
    @ResponseBody
    public ResponseEntity<CompteRepresentation> getCompte(@PathVariable int idCompte){
        CompteRepresentation compteRepresentation = this.webCompteService.getCompte(idCompte);
        return new ResponseEntity<>(compteRepresentation, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CompteRepresentation> createCompte(@RequestBody CompteRepresentation compteRepresentation){
        compteRepresentation = this.webCompteService.createCompte(compteRepresentation);
        return new ResponseEntity<>(compteRepresentation, HttpStatus.OK);
    }

    @PutMapping("/payment/{idCompte}")
    @ResponseBody
    public ResponseEntity<CompteRepresentation> payCart(@PathVariable("idCompte") int idCompte, @RequestBody CartRepresentation cartRepresentation){
        CompteRepresentation compteRepresentation = this.webCompteService.payCart(idCompte, cartRepresentation);
        if(compteRepresentation.getId() == 0){
            return new ResponseEntity<>(compteRepresentation, HttpStatus.PAYMENT_REQUIRED);
        }
        else {
            return new ResponseEntity<>(compteRepresentation, HttpStatus.OK);
        }
    }
}