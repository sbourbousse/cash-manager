package epitech.backend.web.compte.service;

import epitech.backend.web.cart.representation.CartRepresentation;
import epitech.backend.web.compte.representation.CompteRepresentation;

import java.util.List;

/**
 * Service applicatif pour les budgets
 */
public interface WebCompteService {

    /**
     * Récupération d'un compte
     * @param idCompte l'id du compte à récupérer
     * @return le compte
     */
    CompteRepresentation getCompte(int idCompte);

    CompteRepresentation createCompte(CompteRepresentation compteRepresentation);

    CompteRepresentation payCart(int idCompte, CartRepresentation cartRepresentation);
}