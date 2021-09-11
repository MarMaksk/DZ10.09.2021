package Test;

import Task.Product;
import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {
    //Почему-то когда пишу в аннотации то слово excpected подчеркивает.
    // Нашёл такой способ проверять исключения.
    // Пишут так правильнее
    @Test
    public void ProductBarcodeException() {
        Throwable thrown = assertThrows(BarcodeException.class, () -> {
            new Product("Test", 1, 1, UnitType.gram, "012345678912", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void ProductBarcodeExceptionNoNumbers() {
        Throwable thrown = assertThrows(BarcodeException.class, () -> {
            new Product("Test", 1, 1, UnitType.gram, "QWERTYUIOPASD", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void ProductBarcodeExceptionNull() {
        Throwable thrown = assertThrows(BarcodeException.class, () -> {
            new Product("Test", 1, 1, UnitType.gram, null, ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void PriceProductException() {
        Throwable thrown = assertThrows(PriceProductException.class, () -> {
            new Product("Test", 1, 100, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void PriceProductExceptionNegative() {
        Throwable thrown = assertThrows(PriceProductException.class, () -> {
            new Product("Test", 1, -10, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void NameProductException() {
        Throwable thrown = assertThrows(NameProductException.class, () -> {
            new Product("Tt", 1, 1, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void NameProductExceptionOverLenght() {
        Throwable thrown = assertThrows(NameProductException.class, () -> {
            String str = "s";
            for (int i = 0; i < 250; i++) {
                str += "s";
            }
            new Product(str, 1, 1, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void NameProductExceptionNull() {
        Throwable thrown = assertThrows(NameProductException.class, () -> {
            new Product(null, 1, 1, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void actionTypeNull() throws BarcodeException, PriceProductException, NameProductException {
        Product prod = new Product
                ("Test", 1, 1, UnitType.gram, "1234567890123", null);
        assertEquals(ActionType.NO_ACTION, prod.getActionType());
    }

    @Test
    public void unitTypeNull() throws BarcodeException, PriceProductException, NameProductException {
        Product prod = new Product
                ("Test", 1, 1, null, "1234567890123", ActionType.NO_ACTION);
        assertEquals(UnitType.NOT_CHOSEN, prod.getUnitType());
    }
}