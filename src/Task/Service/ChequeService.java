package Task.Service;

import Task.Product;

import java.util.List;

public class ChequeService {

    private List<Product> chequeList;

    public ChequeService(List<Product> chequeList) {
        this.chequeList = chequeList;
    }

    public double payment() {
        double sum = 0;
        if (chequeList != null)
        for (Product list : chequeList) {
            sum += list.getPrice().doubleValue();
        }
        return sum;
    }
}
