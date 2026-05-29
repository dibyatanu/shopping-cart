package uk.mercator.group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CartServiceTest {

    @Nested
    class ShoppingCart {

        @DisplayName("Should return 25p for 1 orange")
        @Test
        void shouldReturn25pForOneOrange() {
            var cartService = new CartService();
            var actualCost = cartService.checkOut(Fruits.ORANGES);
            assertThat(actualCost, is(equalTo("0.25p")));
        }

        @DisplayName("Should return 60p for 1 apple")
        @Test
        void shouldReturn60pForOneApple() {
            var cartService = new CartService();
            var actualCost = cartService.checkOut(Fruits.APPLE);
            assertThat(actualCost, is(equalTo("0.60p")));
        }

        @DisplayName("Should return £2.05 for 3 apples & 1 orange")
        @Test
        void shouldReturn3ApplesAndOneOrange() {
            var cartService = new CartService();
            var actualCost = cartService.checkOut(Fruits.APPLE,Fruits.APPLE,Fruits.APPLE,Fruits.ORANGES);
            assertThat(actualCost, is(equalTo("£2.05")));
        }
        @DisplayName("Should return £2.55 for 3 apples & 3 orange")
        @Test
        void shouldReturn3ApplesAndTenOrange() {
            var cartService = new CartService();
            var actualCost = cartService.checkOut(Fruits.APPLE,Fruits.APPLE,Fruits.APPLE,Fruits.ORANGES
                    ,Fruits.ORANGES,Fruits.ORANGES);
            assertThat(actualCost, is(equalTo("£2.55")));
        }
    }
}
