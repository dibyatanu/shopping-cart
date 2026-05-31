package uk.mercator.group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

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
            assertThat(actualCost, is(equalTo("£1.45")));
        }
        @DisplayName("Should return £2.55 for 3 apples & 3 orange")
        @Test
        void shouldReturn3ApplesAndTenOrange() {
            var cartService = new CartService();
            var actualCost = cartService.checkOut(Fruits.APPLE,Fruits.APPLE,Fruits.APPLE,Fruits.ORANGES
                    ,Fruits.ORANGES,Fruits.ORANGES);
            assertThat(actualCost, is(equalTo("£1.95")));
        }
    }

    @Nested
    class SimpleOffers {
        @ParameterizedTest(name = "{0}")
        @MethodSource("appleOfferTestCases")
        @DisplayName("Should apply buy get one free on apples")
        void shouldApplyAppleOfferCorrectly(String displayName, int appleCount, String expectedCost) {
            var cartService = new CartService();
            Fruits[] apples = Collections.nCopies(appleCount, Fruits.APPLE)
                    .toArray(Fruits[]::new);
            var actualCost = cartService.checkOut(apples);
            assertThat(actualCost, is(equalTo(expectedCost)));
        }

        private static Stream<Arguments> appleOfferTestCases() {
            return Stream.of(
                    Arguments.of("Should pay for 1 apple when I buy 1", 1, "0.60p"),
                    Arguments.of("Should pay for 1 apple when I buy 2", 2, "0.60p"),
                    Arguments.of("Should pay for 2 apples when I buy 3", 3, "£1.20"),
                    Arguments.of("Should pay for 3 apples when I buy 5", 5, "£1.80")
            );
        }

    }
}
