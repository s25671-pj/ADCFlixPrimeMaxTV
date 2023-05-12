package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler.RTEClasses.BadRequestException;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler.RTEClasses.NotFoundException;

@RestControllerAdvice
public class MovieRTE {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        String message;
        HttpStatus status;
        if (e instanceof NotFoundException) {
            message = "Exception: " + e.getLocalizedMessage();
            status = HttpStatus.NOT_FOUND;
        } else if (e instanceof BadRequestException) {
            message = "Exception: " + e.getLocalizedMessage();
            status = HttpStatus.BAD_REQUEST;
        } else {
            message = "Internal server error exception: " + e.getLocalizedMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(message);
    }
}
