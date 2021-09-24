package Task.PreparationService;

import Task.Product;

import java.util.List;

public interface PreparationForChequeInter {
    List<Product> generateCheque(Product... products);
}
