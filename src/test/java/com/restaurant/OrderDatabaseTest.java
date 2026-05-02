package com.restaurant;

import com.restaurant.model.*;
import com.restaurant.singleton.OrderDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderDatabaseTest {

    @BeforeEach
    void resetSingleton() {
        OrderDatabase.resetForTesting();
    }

    @Test
    void testSingletonNotNull() {
        assertNotNull(OrderDatabase.getInstance());
    }

    @Test
    void testSingletonReturnsSameInstance() {
        OrderDatabase db1 = OrderDatabase.getInstance();
        OrderDatabase db2 = OrderDatabase.getInstance();
        assertSame(db1, db2);
    }

    @Test
    void testSaveOrder() {
        OrderDatabase.getInstance().saveOrder(makeOrder(1));
        assertEquals(1, OrderDatabase.getInstance().getOrderCount());
    }

    @Test
    void testGetAllOrdersContainsSaved() {
        Order order = makeOrder(1);
        OrderDatabase.getInstance().saveOrder(order);
        assertTrue(OrderDatabase.getInstance().getAllOrders().contains(order));
    }

    @Test
    void testOrderCountIncrements() {
        OrderDatabase.getInstance().saveOrder(makeOrder(1));
        OrderDatabase.getInstance().saveOrder(makeOrder(2));
        assertEquals(2, OrderDatabase.getInstance().getOrderCount());
    }

    @Test
    void testSaveNullOrderThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> OrderDatabase.getInstance().saveOrder(null));
    }

    @Test
    void testClearRemovesAllOrders() {
        OrderDatabase.getInstance().saveOrder(makeOrder(1));
        OrderDatabase.getInstance().clear();
        assertEquals(0, OrderDatabase.getInstance().getOrderCount());
    }

    @Test
    void testGetAllOrdersIsUnmodifiable() {
        OrderDatabase.getInstance().saveOrder(makeOrder(1));
        assertThrows(UnsupportedOperationException.class,
                () -> OrderDatabase.getInstance().getAllOrders().clear());
    }

    private Order makeOrder(int id) {
        Customer c = new Customer(id, "Клієнт " + id);
        List<Dish> d = List.of(new Dish("Піца", 150.0));
        return new Order(id, c, d, OrderType.REGULAR);
    }
}
