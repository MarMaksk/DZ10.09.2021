package Test;

import Task.PreparationService.PreparationForCheque;
import Task.Product;
import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreparationForChequeTest {
    private Product prod = new Product("Chees", 10, 50, UnitType.kilo, "1000000000001",
            ActionType.NO_ACTION);
    private List<Product> productList = new ArrayList<>(Arrays.asList(prod));

    PreparationForChequeTest() throws BarcodeException, PriceProductException, NameProductException {
    }

    @org.junit.jupiter.api.Test
    void generateCheque() {
        List<Product> productList = new ArrayList<>(Arrays.asList(prod));
        List<Product> pf = new PreparationForCheque().generateCheque(prod);
        assertTrue(productList.equals(pf));
    }
}