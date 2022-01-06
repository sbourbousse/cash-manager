package epitech.backend.domain.compte.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "comptes")
@Table(name = "comptes")
@Data
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "solde")
    private float solde;
    
    @Override
    public String toString(){
        return "Compte{" +
                "id=" + id +
                '}';
    }
}