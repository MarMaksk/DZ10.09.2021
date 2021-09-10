package Test;

import Task.Product;
import Task.Service.BarcodeService;
import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeServiceTest {

    @Test
    void searchBarcode() throws BarcodeException, PriceProductException, NameProductException {
        Product product = new Product("Dirol", 1, 1, UnitType.gram, "1000000000014",
                ActionType.SEASON_SALE);
        assertEquals(product.getBarcode(), new BarcodeService().searchBarcode("1000000000014").getBarcode());
    }

    @Test
    void searchBarcodeNull() throws BarcodeException, PriceProductException, NameProductException {
        Product product = new Product();
        assertEquals(product.getBarcode(), new BarcodeService().searchBarcode(null).getBarcode());
    }
    @Test
    void searchBarcodeUncorrectBarcode() throws BarcodeException, PriceProductException, NameProductException {
        Product product = new Product();
        assertEquals(product.getBarcode(), new BarcodeService().searchBarcode("213FFF").getBarcode());
    }
}