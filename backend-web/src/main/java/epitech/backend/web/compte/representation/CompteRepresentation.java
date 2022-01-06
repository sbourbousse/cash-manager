package epitech.backend.web.compte.representation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import epitech.backend.web.global.components.DedupingObjectIdResolver;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CompteRepresentation.class, resolver = DedupingObjectIdResolver.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CompteRepresentation {

    private int id;

    private float solde;
}