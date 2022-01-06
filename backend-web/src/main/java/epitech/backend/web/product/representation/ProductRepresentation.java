package epitech.backend.web.product.representation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import epitech.backend.web.global.components.DedupingObjectIdResolver;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductRepresentation.class, resolver = DedupingObjectIdResolver.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductRepresentation {

    private int id;

    private String name;

    private float price;
}
