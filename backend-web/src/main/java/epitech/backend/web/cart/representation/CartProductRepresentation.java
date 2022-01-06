package epitech.backend.web.cart.representation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import epitech.backend.web.global.components.DedupingObjectIdResolver;
import epitech.backend.web.product.representation.ProductRepresentation;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CartProductRepresentation.class, resolver = DedupingObjectIdResolver.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CartProductRepresentation {

    private int id;

    private CartRepresentation cartRepresentation;

    private ProductRepresentation productRepresentation;

    private Integer amount;
}