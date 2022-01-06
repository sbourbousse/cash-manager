package epitech.backend.web.cart.representation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import epitech.backend.web.global.components.DedupingObjectIdResolver;
import lombok.Data;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CartRepresentation.class, resolver = DedupingObjectIdResolver.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CartRepresentation {

    private int id;

    private String u_id;

    private List<CartProductRepresentation> cartProducts;
}
