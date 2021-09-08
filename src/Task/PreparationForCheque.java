package Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreparationForCheque {
    private List<Product> chequeList = new ArrayList<>();

    public List<Product> generateCheque(Product... products) {
        Arrays.stream(products).forEach(chequeList::add);
        return this.chequeList;
    }

    public List<Product> getChequeList() {
        return chequeList;
    }

    public void setChequeList(List<Product> chequeList) {
        this.chequeList = chequeList;
    }
}
