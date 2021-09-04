import java.util.ArrayList;
import java.util.List;

public class Cheque {
    public static double payment(List<Product> chequeList) {
        double sum = 0;
        for (Product list : chequeList) {
            sum += list.getPrice().doubleValue();
        }
        return sum;
    }
}
