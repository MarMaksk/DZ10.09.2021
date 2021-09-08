import Service.ChequeService;
import enums.ActionType;
import enums.UnitType;
import exception.BarcodeException;
import exception.NameProductException;
import exception.PriceProductException;

public class Main {
    public static void main(String[] args) throws NameProductException, PriceProductException, BarcodeException, InterruptedException {
        Product prod = new Product("fff", 2l, 11, UnitType.gram, "1234567890123", ActionType.NO_ACTION);
        //Action action = new Action(40);
        ChequeService cheque = new ChequeService(MiniBD.products(), 40);

        double pay = cheque.payment();
        System.out.println(pay);
    }
}
