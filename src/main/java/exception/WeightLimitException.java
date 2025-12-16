package exception;

public class WeightLimitException extends RuntimeException {
    public WeightLimitException(String message) {
        super(message);
    }
}
