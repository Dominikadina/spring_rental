package pl.sda.arppl4.spring_wypozyczalnia.exception;

public class CarNotAvailableException extends RuntimeException {
    public CarNotAvailableException(String message) {
        super(message);
    }
}
