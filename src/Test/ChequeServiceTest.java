package Test;

import Task.MicroBD;
import Task.Service.ActionService;
import Task.Service.ChequeService;
import Task.exception.BarcodeException;
import Task.exception.NameProductException;
import Task.exception.PriceProductException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChequeServiceTest {

    @Test
    void payment() throws BarcodeException, PriceProductException, NameProductException {
        assertEquals(416.49, new ChequeService(new ActionService(MicroBD.products(),
                40, "07.09.2021", "30.09.2021").applicationOfShares()).payment());
    }

    @Test
    void paymentWithoutAction() throws BarcodeException, PriceProductException, NameProductException {
        assertEquals(544.86, new ChequeService(MicroBD.products()).payment());
    }
    @Test
    void paymentNull() throws BarcodeException, PriceProductException, NameProductException {
        assertEquals(416.49, new ChequeService(new ActionService(MicroBD.products(),
                40, "07.09.2021", "30.09.2021").applicationOfShares()).payment());
    }
}