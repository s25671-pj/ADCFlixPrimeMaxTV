//package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.client.HttpClientErrorException;
//
//@RestControllerAdvice
//public class MovieRTE {
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//        return ResponseEntity.internalServerError().build();
//    }
//
//    @ExceptionHandler(HttpClientErrorException.NotFound.class)
//    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found 404");
//    }
//
//    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
//    public ResponseEntity<String> handleBadRequestException(RuntimeException e) {
//        return ResponseEntity.badRequest().build();
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleNoContentException(RuntimeException e) {
//        return ResponseEntity.noContent().build();
//    }
//}
