package epitech.backend.domain.cart.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "cart")
@Table(name = "cart")
@Data
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "u_id")
    private String u_id = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", u_id='" + u_id + '\'' +
                ", cartProducts=" + cartProducts +
                '}';
    }
}