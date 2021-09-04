import enums.ActionType;
import enums.UnitType;
import exception.BarcodeException;
import exception.NameProductException;
import exception.PriceProductException;

import java.math.BigDecimal;

public class Products {
    private String name;
    private BigDecimal price;
    private UnitType unitType;
    private String barcode;
    private ActionType actionType;

    public Products(String name, long grivni, int peny, UnitType unitType, String barcode, ActionType actionType) throws NameProductException, PriceProductException, BarcodeException {
        if (name.length() < 3 || name.length() > 250)
            throw new NameProductException();
        this.name = name;
        if (peny > 99)
            throw new PriceProductException();
        this.price = BigDecimal.valueOf(grivni).add(BigDecimal.valueOf(peny, 2));
        this.unitType = unitType;
        if (barcode.length() < 13)
            throw new BarcodeException();
        this.barcode = barcode;
        this.actionType = actionType;
    }

}
