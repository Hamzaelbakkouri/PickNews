package ma.youcode.candlelight.exceptions;

public class InvalidCredentials extends RuntimeException {
    
    public InvalidCredentials(String message) {
        super(message);
    }
}
