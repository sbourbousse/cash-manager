package epitech.backend.web.global.exception;

import epitech.backend.web.global.exception.utils.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity validationError(ConstraintViolationException ex, WebRequest request){

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Violation de contraintes");
        apiError.addValidationErrors(ex.getConstraintViolations());
        LOGGER.error("Erreur de validation", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = { MailSendException.class })
    protected ResponseEntity MailSendExceptionError(MailSendException ex, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        LOGGER.error("MailSendExceptionError", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = { FileNotFoundException.class })
    protected ResponseEntity FileNotFoundExceptionError(FileNotFoundException ex, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        LOGGER.error("FileNotFound", ex);
        return buildResponseEntity(apiError);
    }

    @Override
    public ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        logger.info(servletWebRequest.getHttpMethod()+" to " +servletWebRequest.getRequest().getServletPath());
        String error = "Requete JSON mal formatée";
        LOGGER.error(error, ex);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                       HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Erreur de validation");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        LOGGER.error("Erreur de validation", ex);
        return buildResponseEntity(apiError);
    }



    /// ------------------------------------------
    /// RESPONSE FORMATTER
    /// ------------------------------------------
    private ResponseEntity buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
