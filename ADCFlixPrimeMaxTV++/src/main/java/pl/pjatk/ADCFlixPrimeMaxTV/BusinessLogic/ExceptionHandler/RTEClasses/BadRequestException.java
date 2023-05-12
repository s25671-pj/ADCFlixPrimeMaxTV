package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler.RTEClasses;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}