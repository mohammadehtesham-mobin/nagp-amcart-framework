package exception;

import constant.AmCartErrorConstant;
import model.dto.AmCartErrorResponse;
import model.exception.AmCartException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AmCartException.class)
    public ResponseEntity<AmCartErrorResponse> handleNoSuchElementException(AmCartException amCartException) {
        return buildAmCartErrorResponse(amCartException.getErrorCode(),
                amCartException.getErrorMessage(),
                amCartException.getHttpStatusCode(), null);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Customize the error response to include all validation errors
        AmCartErrorResponse errorResponse = new AmCartErrorResponse(
                "VALIDATION_FAILED",
                "Validation failed for one or more fields",
                new Date(), errors
        );
        errorResponse.setErrorDetails(errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AmCartErrorResponse> globalExceptionHandling(Exception exception, WebRequest request) {
        return buildAmCartErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                AmCartErrorConstant.INTERNAL_SERVER_ERROR_MSG,
                HttpStatus.INTERNAL_SERVER_ERROR, null);
    }


    private static ResponseEntity<AmCartErrorResponse> buildAmCartErrorResponse(String errorCode, String errorMessage, HttpStatus httpStatusCode
            , Map<String, String> errorDetails) {
        return new ResponseEntity<>(
                new AmCartErrorResponse(errorCode, errorMessage, new Date(), errorDetails),
                httpStatusCode);
    }
}

