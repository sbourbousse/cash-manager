package epitech.backend.web.global.exception.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
public class ApiError {

	/*** HTTP status code for the error.
	 * https://httpstat.us
	 */
	private HttpStatus status;

	/*** Date and time of the error */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	/*** Error Message (custom the majority of time)*/
	private String message;

	/*** Error Message (From the exception)*/
	private String debugMessage;

	/*** List of all Subsidiary error which can occur */
	@Setter(AccessLevel.NONE)
	private List<ApiAuthSubError> subErrors;

	/// ------------------------------------------
	/// CONSTRUCTOR
	/// ------------------------------------------

	/** Default Constructor */
	private ApiError() {
		timestamp = LocalDateTime.now();
		subErrors = new ArrayList<>();
	}

	/**
	 * Light/Dummy Constructor for preparation of HTTP status of the request
	 * @param status    HTTP Status of the request
	 */
	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	/**
	 * Complete Constructor for Exception formatting
	 * @param status    HTTP Status of the request
	 * @param message   Custom Message to display
	 * @param ex        Exception to format
	 */
	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}


	/// ------------------------------------------
	/// METHODS
	/// ------------------------------------------

	/**
	 * Add Subsidiary error to the returned Exception
	 * @param subError  Subsidiary error
	 */
	private void addSubError(ApiAuthSubError subError) {
		subErrors.add(subError);
	}

	/**
	 * Add Complete Validation error to the subsidiary error list of the current exception
	 * @param object        Object to consider
	 * @param field         Field concern by the exception
	 * @param rejectedValue Input value given
	 * @param message       Custom Message
	 */
	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object, field, rejectedValue, message));
	}

	/**
	 * Add Simple Validation error to the subsidiary error list of the current exception
	 * @param object        Object to consider
	 * @param message       Custom Message
	 */
	private void addValidationError(String object, String message) {
		addSubError(new ApiValidationError(object, message));
	}

	/**
	 * Add Validation error to the subsidiary error list of the current exception
	 * from the object FieldError
	 * @param fieldError    Object containing all validation error informations
	 */
	private void addValidationError(FieldError fieldError) {
		this.addValidationError(
				fieldError.getObjectName(),
				fieldError.getField(),
				fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}

	/**
	 * Add multiple errors to the subsidiary error list of the current exception
	 * @param fieldErrors   List of Errors to add
	 */
	public void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}

	/**
	 * Add error to the subsidiary error list of the current exception
	 * @param objectError   Object error info to add
	 */
	private void addValidationError(ObjectError objectError) {
		this.addValidationError(
				objectError.getObjectName(),
				objectError.getDefaultMessage());
	}

	/**
	 * Add errors to the subsidiary error list of the current exception
	 * @param globalErrors   List of Object error info to add
	 */
	public void addValidationError(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addValidationError);
	}

	/**
	 * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
	 *
	 * @param cv the ConstraintViolation
	 */
	private void addValidationError(ConstraintViolation<?> cv) {
		this.addValidationError(
				cv.getRootBeanClass().getSimpleName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
				cv.getInvalidValue(),
				cv.getMessage());
	}

	public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addValidationError);
	}

	@Override
	public String toString() {
		return "ApiError{" +
				"status=" + status +
				", timestamp=" + timestamp +
				", message='" + message + '\'' +
				", debugMessage='" + debugMessage + '\'' +
				", subErrors=" + subErrors +
				'}';
	}
}

