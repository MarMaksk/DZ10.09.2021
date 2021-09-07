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

    private int count = 0;
    private boolean secondProd = false;

    public void actionForSecondProduct() {
        if (actionSecond == null)
            return;
        Thread th = new Thread(() -> check(actionSecond.size()));
        th.start();
        actionSecond.sort((x, y) -> {
            if (x.getPrice().doubleValue() < y.getPrice().doubleValue()) return 1;
            if (x.getPrice().doubleValue() > y.getPrice().doubleValue()) return -1;
            if (x.getPrice().doubleValue() == y.getPrice().doubleValue()) return 0;
            return 0;
        });
        try {
            th.join();
            for (int i = 0; i < count; i++)
                actionSecond.get(i).setPrice(BigDecimal.valueOf(1, 2));
            if (secondProd)
                actionSecond.get(count + 1).setPrice(actionSecond.get(count + 1)
                        .getPrice().multiply(BigDecimal.valueOf(0.5)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void check(int length) {
        if (length == 1)
            return;
        else if (length == 2) {
            secondProd = true;
            return;
        } else if (length >= 3) {
            count++;
            check(length - 3);
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
