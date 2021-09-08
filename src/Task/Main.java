package Task;

import Task.Service.ActionService;
import Task.Service.ChequeService;
import Task.enums.ActionType;
import Task.enums.UnitType;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;


public class Main {
    public static void main(String[] args) throws NameProductException, PriceProductException, BarcodeException, InterruptedException {
        Product prod = new Product("fff", 2l, 11, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        ChequeService cheque = new ChequeService(new ActionService(MiniBD.products(),
                40, "07-09-2021", "09-09-2021").applicationOfShares());
        double pay = cheque.payment();
        System.out.println(pay);
    }
}
