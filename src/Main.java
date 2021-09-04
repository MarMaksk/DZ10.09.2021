import enums.ActionType;
import enums.UnitType;
import exception.BarcodeException;
import exception.NameProductException;
import exception.PriceProductException;

public class Main {
    public static void main(String[] args) throws NameProductException, PriceProductException, BarcodeException {
        Products prod = new Products("fff", 2l, 11, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
    }
}
