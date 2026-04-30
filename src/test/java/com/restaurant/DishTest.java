package com.restaurant;

import com.restaurant.model.Dish;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    @Test
    void testDishCreation() {
        Dish dish = new Dish("Піца", 150.0);
        assertNotNull(dish);
    }

    @Test
    void testDishGetName() {
        Dish dish = new Dish("Піца", 150.0);
        assertEquals("Піца", dish.getName());
    }

    @Test
    void testDishGetPrice() {
        Dish dish = new Dish("Борщ", 80.0);
        assertEquals(80.0, dish.getPrice());
    }

    @Test
    void testDishZeroPriceAllowed() {
        Dish dish = new Dish("Хліб", 0.0);
        assertEquals(0.0, dish.getPrice());
    }

    @Test
    void testDishNegativePriceThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Dish("Суп", -10.0));
    }

    @Test
    void testDishNullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Dish(null, 50.0));
    }

    @Test
    void testDishBlankNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Dish("   ", 50.0));
    }

    @Test
    void testDishToString() {
        Dish dish = new Dish("Піца", 150.0);
        assertTrue(dish.toString().contains("Піца"));
    }
}
