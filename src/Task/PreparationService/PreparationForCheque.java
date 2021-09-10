package Task.PreparationService;

import Task.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreparationForCheque {

    public List<Product> generateCheque(Product... products) {
        List<Product> chequeList = new ArrayList<>();
        if (products!=null)
        Arrays.stream(products).forEach(chequeList::add);
        return chequeList;
    }
}
