package epitech.backend.web.global.exception.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @class ApiValidationError
 *
 * Concrete implementation for subsidiary error.
 * This class handle all Validation errors from received values.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends ApiAuthSubError{
	/*** Name of the Concerned Object */
	protected String object;

	/*** Field which doesn't respect validation criteria */
	protected String field;

	/*** Bad Input Value */
	protected Object rejectedValue;

	/*** Custom Error Message to display for the exception */
	protected String message;

	/*** Default constructor */
	public ApiValidationError(){}

	/**
	 * Partial Constructor
	 * @param object        Object Concerned
	 * @param message       Custom Error Message to display for the exception
	 */
	public ApiValidationError(String object, String message){
		this();
		this.object = object;
		this.message = message;
	}

	/**
	 * Full Constructor
	 * @param object        Object Concerned
	 * @param field         Field which doesn't respect validation criteria
	 * @param rejectedValue Bad Input Value
	 * @param message       Custom Error Message to display for the exception
	 */
	public ApiValidationError(String object, String field, Object rejectedValue, String message){
		this();
		this.object = object;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}
}
