package uk.mercator.group;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class CartService {
    final private Map<Fruits,Integer> fruitBasket = new HashMap<>();

    public String checkOut(final Fruits... fruits) {
        addToBasket(fruits);
        double total = getTotal();
        return formatCurrency(total/100);
    }


    private void addToBasket(final Fruits... fruits) {
        Arrays.stream(fruits)
                .forEach(fruit -> fruitBasket.merge(fruit, 1, Integer::sum));
    }

    private double getTotal() {
        double total = 0;
        total += fruitBasket.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getUnitCost() * entry.getValue())
                .sum();
        return total;
    }

    public static String formatCurrency(double value) {
        return (value >= 1)
                ? String.format("£%.2f", value)
                : String.format("%.2fp",value);
    }
}
