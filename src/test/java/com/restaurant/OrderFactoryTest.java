package com.restaurant;

import com.restaurant.factory.BulkOrderFactory;
import com.restaurant.factory.OrderFactory;
import com.restaurant.factory.RegularOrderFactory;
import com.restaurant.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    private Customer customer;
    private List<Dish> dishes;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Тест");
        dishes = List.of(new Dish("Піца", 150.0));
    }

    @Test
    void testRegularFactoryCreatesOrder() {
        OrderFactory factory = new RegularOrderFactory();
        Order order = factory.createOrder(1, customer, dishes);
        assertNotNull(order);
    }

    @Test
    void testRegularFactoryCreatesRegularType() {
        OrderFactory factory = new RegularOrderFactory();
        Order order = factory.createOrder(1, customer, dishes);
        assertEquals(OrderType.REGULAR, order.getType());
    }

    @Test
    void testBulkFactoryCreatesOrder() {
        OrderFactory factory = new BulkOrderFactory();
        Order order = factory.createOrder(1, customer, dishes);
        assertNotNull(order);
    }

    @Test
    void testBulkFactoryCreatesBulkType() {
        OrderFactory factory = new BulkOrderFactory();
        Order order = factory.createOrder(1, customer, dishes);
        assertEquals(OrderType.BULK, order.getType());
    }

    @Test
    void testFactorySetsCorrectCustomer() {
        OrderFactory factory = new RegularOrderFactory();
        Order order = factory.createOrder(1, customer, dishes);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void testFactorySetsCorrectId() {
        OrderFactory factory = new RegularOrderFactory();
        Order order = factory.createOrder(7, customer, dishes);
        assertEquals(7, order.getId());
    }

    @Test
    void testFactoryCreatedOrderIsPending() {
        OrderFactory factory = new BulkOrderFactory();
        Order order = factory.createOrder(1, customer, dishes);
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }

    @Test
    void testDifferentFactoriesProduceDifferentTypes() {
        Order regular = new RegularOrderFactory().createOrder(1, customer, dishes);
        Order bulk = new BulkOrderFactory().createOrder(2, customer, dishes);
        assertNotEquals(regular.getType(), bulk.getType());
    }
}
