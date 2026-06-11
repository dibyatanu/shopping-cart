package uk.mercator.group;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public final class CartService {

    private final Function<Map.Entry<Fruits, Integer>, Map.Entry<Fruits, Integer>> applyOffers =
            basket -> {
                switch (basket.getKey()) {
                    case APPLE -> applyOffersOnApple(basket);
                    case ORANGES -> applyOffersOnOranges(basket);
                }
                return basket;
            };

    public String checkOut(final Fruits... fruits) {
        Map<Fruits,Integer> basket = buildBasket(fruits);
        double total = calculateTotalPrice(basket);
        return formatCurrency(total/100);
    }


    private Map<Fruits,Integer> buildBasket(final Fruits... fruits) {
        return Arrays.stream(fruits)
                     .collect(Collectors.groupingBy(
                             Function.identity(),
                             Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));

    }

    private double calculateTotalPrice(final Map<Fruits,Integer> basket) {
        double total = 0;
        total += basket.entrySet()
                .stream()
                .map(applyOffers)
                .mapToDouble(entry -> entry.getKey().getUnitCost() * entry.getValue())
                .sum();
        return total;
    }

    /**
     * Applies buy one, get one apple free
     * @param entry
     */

    private void applyOffersOnApple(final Map.Entry<Fruits, Integer> entry) {
            entry.setValue((entry.getValue() + 1) / 2);
    }

    /**
     * Applies 3 for the price of 2 on oranges
     * @param entry
     */
    private  void applyOffersOnOranges(final Map.Entry<Fruits, Integer> entry) {
        int qty = entry.getValue();
        int groupsOfThree = qty / 3;
        int remainder = qty % 3;
        int quantity =groupsOfThree * 2 + remainder;
        entry.setValue(quantity);
    }

    private String formatCurrency(final double value) {
        return (value >= 1)
                ? String.format("£%.2f", value)
                : String.format("%.2fp",value);
    }
}
