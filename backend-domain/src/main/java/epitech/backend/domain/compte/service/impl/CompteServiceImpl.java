package epitech.backend.domain.compte.service.impl;

import epitech.backend.domain.compte.model.Compte;
import epitech.backend.domain.compte.repository.CompteRepository;
import epitech.backend.domain.compte.service.CompteService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CompteServiceImpl implements CompteService {

    private CompteRepository compteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte getCompte(int idCompte) {
        return this.compteRepository.getOne(idCompte);
    }

    @Override
    public Compte createCompte(Compte compte) {
        compte = this.compteRepository.save(compte);
        return compte;
    }

    @Override
    public Compte saveCompte(Compte compte) {
        return this.compteRepository.save(compte);
    }
}