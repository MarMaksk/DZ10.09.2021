package Task.exception;

public class BarcodeException extends Exception {
    public BarcodeException() {
        super("Длина штрихкода должна быть 13 цифр");
    }
}
