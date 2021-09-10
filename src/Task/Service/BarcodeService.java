package Task.Service;

import Task.MicroBD;
import Task.Product;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;

import java.util.Optional;

public class BarcodeService {
    public static Product searchBarcode(String barcode)
            throws BarcodeException, PriceProductException, NameProductException {
        Optional<Product> prod = MicroBD.products().stream().filter(el -> el.getBarcode().equals(barcode)).findFirst();
        Product result = prod.orElse(new Product());
        return result;
    }
}
