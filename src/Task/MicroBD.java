package Task;

import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MicroBD {
    //Для этого класса нет тестов т.к. это лишь имитация БД
    public static List<Product> products() throws BarcodeException, PriceProductException, NameProductException {
        List<Product> baseProd = new ArrayList(Arrays.asList(
                new Product("Chees", 10, 50, UnitType.kilo, "1000000000001",
                        ActionType.NO_ACTION),
                new Product("Milk", 2, 50, UnitType.liter, "1000000000002",
                        ActionType.FOR_SECOND_PRODUCT),
                new Product("Water", 1, 20, UnitType.liter, "1000000000003",
                        ActionType.FOR_SECOND_PRODUCT),
                new Product("Watermelon", 10, 5, UnitType.kilo, "1000000000004",
                        ActionType.FOR_SECOND_PRODUCT),
                new Product("Tank", 5, 5, UnitType.thing, "1000000000005",
                        ActionType.EVERY_THREE),
                new Product("Coco", 50, 0, UnitType.gram, "1000000000006",
                        ActionType.EVERY_THREE),
                new Product("Better", 1, 0, UnitType.kilo, "1000000000007",
                        ActionType.NO_ACTION),
                new Product("Meter", 2, 30, UnitType.liter, "1000000000008",
                        ActionType.FOR_SECOND_PRODUCT),
                new Product("Icecream", 1, 20, UnitType.kilo, "1000000000009",
                        ActionType.FOR_SECOND_PRODUCT),
                new Product("Sticker", 5, 0, UnitType.thing, "1000000000010",
                        ActionType.FOR_SECOND_PRODUCT),
                new Product("Stick", 5, 5, UnitType.thing, "1000000000011",
                        ActionType.EVERY_THREE),
                new Product("Chiller", 150, 0, UnitType.thing, "1000000000012",
                        ActionType.EVERY_THREE),
                new Product("Wattsan fl tt", 300, 0, UnitType.thing, "1000000000013",
                        ActionType.SEASON_SALE),
                new Product("Dirol", 1, 1, UnitType.gram, "1000000000014",
                        ActionType.SEASON_SALE)
        ));
        return baseProd;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
