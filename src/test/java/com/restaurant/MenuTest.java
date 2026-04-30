package com.restaurant;

import com.restaurant.model.Dish;
import com.restaurant.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;
    private Dish pizza;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        pizza = new Dish("Піца", 150.0);
    }

    @Test
    void testMenuIsEmptyInitially() {
        assertTrue(menu.isEmpty());
    }

    @Test
    void testAddDishToMenu() {
        menu.addDish(pizza);
        assertTrue(menu.containsDish(pizza));
    }

    @Test
    void testMenuSizeAfterAdd() {
        menu.addDish(pizza);
        menu.addDish(new Dish("Борщ", 80.0));
        assertEquals(2, menu.size());
    }

    @Test
    void testMenuNotEmptyAfterAdd() {
        menu.addDish(pizza);
        assertFalse(menu.isEmpty());
    }

    @Test
    void testMenuDoesNotContainUnadded() {
        Dish notAdded = new Dish("Суші", 200.0);
        assertFalse(menu.containsDish(notAdded));
    }

    @Test
    void testAddNullDishThrows() {
        assertThrows(IllegalArgumentException.class, () -> menu.addDish(null));
    }

    @Test
    void testGetDishesIsUnmodifiable() {
        menu.addDish(pizza);
        assertThrows(UnsupportedOperationException.class, () -> menu.getDishes().add(new Dish("Суп", 60.0)));
    }

    @Test
    void testGetDishesContainsAdded() {
        menu.addDish(pizza);
        assertTrue(menu.getDishes().contains(pizza));
    }
}
