import enums.ActionType;
import enums.UnitType;
import exception.BarcodeException;
import exception.NameProductException;
import exception.PriceProductException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MiniBD {
    public static void products() throws BarcodeException, PriceProductException, NameProductException {
        List<Product> baseProd = new ArrayList(Arrays.asList(
                new Product("Chees", 10, 50, UnitType.kilo, "1000000000001", ActionType.NO_ACTION),
                new Product("Milk", 2, 50, UnitType.liter, "1000000000002", ActionType.FOR_SECOND_PRODUCT),
                new Product("Water", 1, 20, UnitType.liter, "1000000000003", ActionType.FOR_SECOND_PRODUCT),
                new Product("Watermelon", 10, 5, UnitType.kilo, "1000000000004", ActionType.FOR_SECOND_PRODUCT),
                new Product("Tank", 5, 5, UnitType.thing, "1000000000005", ActionType.EVERY_THREE),
                new Product("Coco", 50, 0, UnitType.gram, "1000000000006", ActionType.EVERY_THREE)
        ));
    }
}
