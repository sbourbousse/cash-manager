package epitech.backend.web.compte.service.impl;

import epitech.backend.domain.cart.model.Cart;
import epitech.backend.domain.cart.service.CartService;
import epitech.backend.domain.compte.model.Compte;
import epitech.backend.domain.compte.service.CompteService;
import epitech.backend.web.cart.representation.CartRepresentation;
import epitech.backend.web.compte.representation.CompteRepresentation;
import epitech.backend.web.compte.service.WebCompteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Service
@Transactional
public class WebCompteServiceImpl implements WebCompteService {

    private CompteService compteService;
    private CartService cartService;

    @Inject
    public WebCompteServiceImpl(CompteService compteService, CartService cartService) {
        this.compteService = compteService;
        this.cartService = cartService;
    }

    @Override
    public CompteRepresentation getCompte(int idCompte) {
        ModelMapper modelMapper = new ModelMapper();
        Compte compte = this.compteService.getCompte(idCompte);
        return modelMapper.map(compte, CompteRepresentation.class);
    }

    @Override
    public CompteRepresentation createCompte(CompteRepresentation compteRepresentation) {
        ModelMapper modelMapper = new ModelMapper();
        Compte compte = modelMapper.map(compteRepresentation, Compte.class);
        compte = this.compteService.createCompte(compte);
        compteRepresentation = modelMapper.map(compte, CompteRepresentation.class);
        return compteRepresentation;
    }

    @Override
    public CompteRepresentation payCart(int idCompte, CartRepresentation cartRepresentation) {
        ModelMapper modelMapper = new ModelMapper();
        Compte compte = this.compteService.getCompte(idCompte);
        System.out.println(compte.getSolde());

        float total = this.cartService.getTotalCart(cartRepresentation.getId());

        if(total > compte.getSolde()){
            return new CompteRepresentation();
        }
        else{
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            compte.setSolde(Math.round((compte.getSolde() - total)*100)/100);
            compte = this.compteService.saveCompte(compte);
            this.cartService.deleteCart(cartRepresentation.getId());
            return modelMapper.map(compte, CompteRepresentation.class);
        }
    }
}