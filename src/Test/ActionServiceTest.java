package Test;

import Task.MicroBD;
import Task.Product;
import Task.Service.ActionService;
import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionServiceTest {
    //В этом списке продукты с изменёнными ценами по соответствующим акциям
    List<Product> baseProd = new ArrayList(Arrays.asList(
            new Product("Chees", 10, 50, UnitType.kilo, "1000000000001",
                    ActionType.NO_ACTION),
            new Product("Milk", 2, 50, UnitType.liter, "1000000000002",
                    ActionType.FOR_SECOND_PRODUCT),
            new Product("Water", 0, 01, UnitType.liter, "1000000000003",
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
            new Product("Icecream", 0, 01, UnitType.kilo, "1000000000009",
                    ActionType.FOR_SECOND_PRODUCT),
            new Product("Sticker", 5, 0, UnitType.thing, "1000000000010",
                    ActionType.FOR_SECOND_PRODUCT),
            new Product("Stick", 0, 01, UnitType.thing, "1000000000011",
                    ActionType.EVERY_THREE),
            new Product("Chiller", 150, 0, UnitType.thing, "1000000000012",
                    ActionType.EVERY_THREE),
            new Product("Wattsan fl tt", 180, 0, UnitType.thing, "1000000000013",
                    ActionType.SEASON_SALE),
            new Product("Dirol", 0, 6, UnitType.gram, "1000000000014",
                    ActionType.SEASON_SALE)
    ));

    ActionServiceTest() throws BarcodeException, PriceProductException, NameProductException {
    }

    @Test
    void applicationOfShares() throws BarcodeException, PriceProductException, NameProductException {
        assertFalse(MicroBD.products().
                equals(new ActionService(MicroBD.products(), 40, "08.09.2021", "30.09.2021")
                        .applicationOfShares()));
    }

    @Test
    void applicationOfSharesSecond() throws BarcodeException, PriceProductException, NameProductException {
        List<Product> list = new ActionService(MicroBD.products(), 40, "08.09.2021", "30.09.2021")
                .applicationOfShares();
        for (Product ls : list) {
            for (Product bp : baseProd)
                if (ls.getBarcode().equals(bp.getBarcode()))
                    assertTrue(ls.getPrice().equals(bp.getPrice()));
        }

    }
}