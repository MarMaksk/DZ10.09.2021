package Task.exception;

public class PriceProductException extends Exception{
    public PriceProductException() {
        super("Не больше 2-х цифр после запятой");
    }
}
