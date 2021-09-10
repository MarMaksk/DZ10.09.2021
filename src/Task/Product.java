package Task;

import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private UnitType unitType;
    private String barcode;
    private ActionType actionType;

    public Product() {
    }

    public Product(String name, long grivni, int peny, UnitType unitType, String barcode, ActionType actionType)
            throws NameProductException, PriceProductException, BarcodeException {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
