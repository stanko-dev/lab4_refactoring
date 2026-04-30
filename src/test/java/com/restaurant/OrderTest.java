package com.restaurant;

import com.restaurant.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Customer customer;
    private List<Dish> dishes;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Іван");
        dishes = List.of(new Dish("Піца", 150.0), new Dish("Кола", 40.0));
    }

    @Test
    void testOrderCreation() {
        Order order = new Order(1, customer, dishes, OrderType.REGULAR);
        assertNotNull(order);
    }

    @Test
    void testOrderAssociatedWithCustomer() {
        Order order = new Order(1, customer, dishes, OrderType.REGULAR);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void testOrderDefaultStatusIsPending() {
        Order order = new Order(1, customer, dishes, OrderType.REGULAR);
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }

    @Test
    void testOrderTotalPrice() {
        Order order = new Order(1, customer, dishes, OrderType.REGULAR);
        assertEquals(190.0, order.getTotalPrice(), 0.001);
    }

    @Test
    void testOrderStatusChange() {
        Order order = new Order(1, customer, dishes, OrderType.REGULAR);
        order.setStatus(OrderStatus.CONFIRMED);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void testOrderNullCustomerThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order(1, null, dishes, OrderType.REGULAR));
    }

    @Test
    void testOrderEmptyDishesThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order(1, customer, List.of(), OrderType.REGULAR));
    }

    @Test
    void testOrderGetType() {
        Order order = new Order(1, customer, dishes, OrderType.BULK);
        assertEquals(OrderType.BULK, order.getType());
    }

    @Test
    void testOrderGetId() {
        Order order = new Order(99, customer, dishes, OrderType.REGULAR);
        assertEquals(99, order.getId());
    }

    @Test
    void testOrderDishesIsUnmodifiable() {
        Order order = new Order(1, customer, dishes, OrderType.REGULAR);
        assertThrows(UnsupportedOperationException.class,
                () -> order.getDishes().add(new Dish("Суп", 50.0)));
    }
}
