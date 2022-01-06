package epitech.backend.domain.compte.service;

import epitech.backend.domain.compte.model.Compte;

import java.util.List;

public interface CompteService {

    Compte getCompte(int idCompte);

	Compte createCompte(Compte compte);

    Compte saveCompte(Compte compte);
}