import enums.ActionType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Action {
    private List<Product> productList;
    private List<Product> actionThree;
    private List<Product> actionSecond;
    private List<Product> actionSeason;
    private List<Product> actionNone;
    private List<Product> productListWithAction;
    private Thread thSort;
    private Thread thEveryThree;
    private Thread thSecond;
    private Thread thSeason;
    private Thread thFinal;

    public Action(/*List<Product> productList*/ int discount) {
        thSort = new Thread(() -> sortByAction());
        thSort.run();
        thEveryThree = new Thread(() -> actionEveryThree());
        thEveryThree.start();
        thSecond = new Thread(() -> actionForSecondProduct());
        thSecond.start();
        thSeason = new Thread(() -> actionSeason(discount));
        thSeason.start();
        thFinal = new Thread(() -> union());
        thFinal.start();
    }

    public void sortByAction() {
        for (Product list : productList) {
            if (list.getActionType() == ActionType.NO_ACTION)
                actionNone.add(list);
            else if (list.getActionType() == ActionType.EVERY_THREE)
                actionThree.add(list);
            else if (list.getActionType() == ActionType.FOR_SECOND_PRODUCT)
                actionSecond.add(list);
            else if (list.getActionType() == ActionType.SEASON_SALE)
                actionSeason.add(list);
        }
    }

    public void actionEveryThree() {
        if (actionThree == null)
            return;
        for (int i = 2; i < actionThree.size(); ) {
            this.actionThree.get(i).setPrice(BigDecimal.valueOf(1, 2));
            i += 3;
        }
    }

    public void actionForSecondProduct() {
        if (actionSecond == null)
            return;
        actionSecond.sort((x, y) -> {
            if (x.getPrice().doubleValue() < y.getPrice().doubleValue()) return 1;
            if (x.getPrice().doubleValue() > y.getPrice().doubleValue()) return -1;
            if (x.getPrice().doubleValue() == y.getPrice().doubleValue()) return 0;
            return 0;
        });
        int secondProd = actionSecond.size() / 2;
        for (int i = 1; i < actionSecond.size(); ) {
            //  this.actionSecond.get(i).setPrice();
        }
    }

    public void actionSeason(int discount) {
        if (actionSeason == null)
            return;
        int disc = 100 - discount;
        for (Product list : actionSeason)
            list.setPrice(list.getPrice().multiply(new BigDecimal(BigInteger.valueOf(disc), 2)));
    }

    public void union() {
        try {
            thEveryThree.join();
            thSecond.join();
            thSeason.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
