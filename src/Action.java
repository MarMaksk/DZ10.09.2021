import enums.ActionType;

import java.util.List;

public class Action {
    private List<Product> productList;
    private List<Product> actionThree;
    private List<Product> actionSecond;
    private List<Product> actionSeason;
    private List<Product> actionNone;
    private Thread thSort;
    private Thread thEveryThree;
    private Thread thSecond;
    private Thread thSeason;
    private Thread thFinal;

    public Action(/*List<Product> productList*/) {
        thSort = new Thread(() -> sortByAction());
        thSort.run();
        thEveryThree = new Thread(() -> everyThree());
        thEveryThree.start();
        thSecond = new Thread(() -> forSecondProduct());
        thSecond.start();
        finalMethod();
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

    public void everyThree() {
        System.out.println(thSort.isAlive());
        if (thSort.isAlive()) {
            try {
                thEveryThree.wait();
                thSort.isAlive();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        if (productList == null)
//            return;
//        for (Product list : productList) {
//
//        }
    }

    public void forSecondProduct() {
        if (productList == null)
            return;
        for (Product list : productList) {

        }
    }

    public void seasonAction() {

    }

    public void finalMethod() {
        try {
            thEveryThree.join();
            thSecond.join();
            System.out.println(thEveryThree.isAlive());
            System.out.println(thSecond.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
