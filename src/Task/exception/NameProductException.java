package Task.exception;

public class NameProductException extends Exception{
    public NameProductException() {
        super("Длина наименования должна быть от 3-х до 250и символов");
    }
}
