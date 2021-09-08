package Task.Service;

import Task.Product;

import java.util.List;

public class ChequeService {

    private List<Product> chequeList;

    public ChequeService(List<Product> prod, int seasonDiscount) {
        this.chequeList = new ActionService(prod, seasonDiscount).applicationOfShares();
    }

    public double payment() {
        double sum = 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Product list : chequeList) {
            sum += list.getPrice().doubleValue();
            System.out.println(list.getName() + " " + list.getPrice());
        }
        return sum;
    }
}
