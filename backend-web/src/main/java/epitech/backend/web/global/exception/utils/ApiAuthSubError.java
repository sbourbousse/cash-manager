package epitech.backend.web.global.exception.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @class abstract ApiAuthSubError
 *
 * This class provide base for all subsidiary exception.
 * This allow to format our Exceptions to make the service compliant with RESTful rules
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ApiAuthSubError {


}