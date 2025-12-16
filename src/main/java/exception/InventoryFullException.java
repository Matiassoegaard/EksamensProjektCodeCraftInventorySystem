package exception;

public class InventoryFullException extends RuntimeException {
    public InventoryFullException(String message) {
        super(message);
    }
}
