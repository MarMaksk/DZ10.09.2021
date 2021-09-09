package Task.Service;

import Task.Product;
import Task.enums.ActionType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActionService {
    private final int seasonDiscount;
    private final LocalDate from;
    private final LocalDate to;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private List<Product> productList;
    private List<Product> actionThree = new ArrayList<>();
    private List<Product> actionSecond = new ArrayList<>();
    private List<Product> actionSeason = new ArrayList<>();
    private List<Product> actionNone = new ArrayList<>();
    private List<Product> productListWithAction = new ArrayList<>();
    private Thread thSort;
    private Thread thEveryThree;
    private Thread thSecond;
    private Thread thSeason;
    private Thread thFinal;
    private int thirdProdOnOffer = 0;
    private boolean secondProdOnOffer = false;

    public ActionService(List<Product> productList, int seasonDiscount, String from, String to) {
        this.from = LocalDate.parse(from, dtf);
        this.to = LocalDate.parse(to, dtf);
        this.productList = productList;
        this.seasonDiscount = seasonDiscount;
    }

    public List<Product> applicationOfShares() {
        thSort = new Thread(() -> sortByAction());
        thSort.run();
        thEveryThree = new Thread(() -> actionEveryThree());
        thEveryThree.start();
        thSecond = new Thread(() -> actionForSecondProduct());
        thSecond.start();
        thSeason = new Thread(() -> actionSeason(seasonDiscount));
        thSeason.start();
        thFinal = new Thread(() -> union());
        thFinal.start();
        try {
            thFinal.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return productListWithAction;
        }
    }

    private void sortByAction() {
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

    private void actionEveryThree() {
        if (actionThree == null)
            return;
        for (int i = 2; i < actionThree.size(); ) {
            this.actionThree.get(i).setPrice(BigDecimal.valueOf(1, 2));
            i += 3;
        }
    }

    private void actionForSecondProduct() {
        if (actionSecond == null)
            return;
        Thread th = new Thread(() -> check(actionSecond.size()));
        th.start();
        actionSecond.sort((x, y) -> {
            if (x.getPrice().doubleValue() > y.getPrice().doubleValue()) return 1;
            if (x.getPrice().doubleValue() < y.getPrice().doubleValue()) return -1;
            if (x.getPrice().doubleValue() == y.getPrice().doubleValue()) return 0;
            return 0;
        });
        try {
            th.join();
            for (int i = 0; i < thirdProdOnOffer; i++)
                actionSecond.get(i).setPrice(BigDecimal.valueOf(1, 2));
            if (secondProdOnOffer)
                actionSecond.get(thirdProdOnOffer + 1).setPrice(actionSecond.get(thirdProdOnOffer + 1)
                        .getPrice().multiply(BigDecimal.valueOf(0.5)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void check(int length) {
        if (length == 1)
            return;
        else if (length == 2) {
            secondProdOnOffer = true;
            return;
        } else if (length >= 3) {
            thirdProdOnOffer++;
            check(length - 3);
        }
    }

    private void actionSeason(int discount) {
        if (actionSeason == null)
            return;
        int disc = 100 - discount;
        if (LocalDate.now().isAfter(from) && LocalDate.now().isBefore(to))
            for (Product list : actionSeason) {
                double price = list.getPrice().multiply(new BigDecimal(BigInteger.valueOf(disc), 2)
                        , new MathContext(4)).doubleValue();
                int x = (int) ((price - Math.floor(price)) * 10);
                list.setPrice(BigDecimal.valueOf((long) price).add(BigDecimal.valueOf(x, 2)));
            }
    }

    private List<Product> union() {
        try {
            Thread.yield();
            thEveryThree.join();
            thSecond.join();
            thSeason.join();
            for (Product ls : actionNone)
                productListWithAction.add(ls);
            for (Product ls : actionSecond)
                productListWithAction.add(ls);
            for (Product ls : actionThree)
                productListWithAction.add(ls);
            for (Product ls : actionSeason)
                productListWithAction.add(ls);

            //Почему если я делаю добавление через лямбду то количество элементов преображается?
            //Т.е. к концу всех добавлений в productListWithAction будет около 30 позиций

//            actionNone.forEach(productListWithAction::add);
//            actionSecond.forEach(productListWithAction::add);
//            actionThree.forEach(productListWithAction::add);
//            actionSeason.forEach(productListWithAction::add);

//            actionNone.forEach(el -> productListWithAction.add(el));
//            actionSecond.forEach(el -> productListWithAction.add(el));
//            actionThree.forEach(el -> productListWithAction.add(el));
//            actionSeason.forEach(el -> productListWithAction.add(el));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return productListWithAction;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
