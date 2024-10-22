package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieTicketPriceCalculatorTest {

    MovieTicketPriceCalculator movieTicketPriceCalculator;

    @BeforeEach
    void setUp() {
        movieTicketPriceCalculator = new MovieTicketPriceCalculator(LocalTime.of(10, 10, 0), LocalTime.of(13, 0, 0), 16, 65);
    }

    @Test
    void regularAdultTicket() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(1, 10, 0), 45);

        assertEquals(2700, price);
    }

    @Test
    void regularSeniorTicket() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(1, 10, 0), 70);

        assertEquals(2300, price);
    }

    @Test
    void regularChildTicket() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(1, 10, 0), 4);

        assertEquals(2400, price);
    }

    @Test
    void matineeAdultTicket() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(11, 10, 0), 45);

        assertEquals(2400, price);
    }

    @Test
    void matineeSeniorTicket() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(11, 10, 0), 90);

        assertEquals(2000, price);
    }

    @Test
    void matineeChildTicket() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(11, 10, 0), 5);

        assertEquals(2100, price);
    }

    @Test
    void checkWhenMatineeIsAtCutoff() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(10, 10, 0), 90);

        assertEquals(2000, price);
    }

    @Test
    void checkMatineeStatusAfter() {
        int price = movieTicketPriceCalculator.computePrice(LocalTime.of(20, 10, 0), 90);

        assertEquals(2300, price);
    }

    @Test
    void invalidMatineeEndTime() {
        LocalTime start = LocalTime.of(10, 10, 0);
        LocalTime end = LocalTime.of(9, 10, 0);
        assertThrows(IllegalArgumentException.class, () ->
                new MovieTicketPriceCalculator(start, end, 16, 65));
    }


}
