package Task.Service;

import Task.Product;

import javax.swing.*;
import java.math.MathContext;
import java.util.List;

public class ChequeService {

    private List<Product> chequeList;

    public ChequeService(List<Product> chequeList) {
        this.chequeList = chequeList;
    }

    public double payment() {
        double sum = 0;
        for (Product list : chequeList) {
            sum += list.getPrice().doubleValue();
            System.out.println(list.getName() + " " + list.getPrice());
        }
        return sum;
    }
}
