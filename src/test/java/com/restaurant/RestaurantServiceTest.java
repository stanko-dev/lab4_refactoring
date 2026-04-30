package com.restaurant;

import com.restaurant.factory.RegularOrderFactory;
import com.restaurant.model.*;
import com.restaurant.observer.ConsoleKitchenObserver;
import com.restaurant.observer.KitchenNotifier;
import com.restaurant.service.RestaurantService;
import com.restaurant.singleton.OrderDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    private RestaurantService service;
    private ConsoleKitchenObserver kitchenObserver;
    private Customer customer;
    private List<Dish> dishes;

    @BeforeEach
    void setUp() throws Exception {
        Method reset = OrderDatabase.class.getDeclaredMethod("resetInstance");
        reset.setAccessible(true);
        reset.invoke(null);
        OrderDatabase.getInstance().clear();

        kitchenObserver = new ConsoleKitchenObserver();
        KitchenNotifier notifier = new KitchenNotifier();
        notifier.subscribe(kitchenObserver);
        service = new RestaurantService(new RegularOrderFactory(), notifier, OrderDatabase.getInstance());
        customer = new Customer(1, "Тест");
        dishes = List.of(new Dish("Піца", 150.0));
    }

    @Test
    void testPlaceOrderReturnsOrder() {
        Order order = service.placeOrder(customer, dishes);
        assertNotNull(order);
    }

    @Test
    void testPlaceOrderNotifiesKitchen() {
        Order order = service.placeOrder(customer, dishes);
        assertTrue(kitchenObserver.hasReceived(order));
    }

    @Test
    void testPlaceOrderSavedToDatabase() {
        Order order = service.placeOrder(customer, dishes);
        assertTrue(OrderDatabase.getInstance().getAllOrders().contains(order));
    }

    @Test
    void testPlaceOrderIncrementsId() {
        service.placeOrder(customer, dishes);
        service.placeOrder(customer, dishes);
        assertEquals(3, service.getNextOrderId());
    }

    @Test
    void testPlaceOrderNullCustomerThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.placeOrder(null, dishes));
    }

    @Test
    void testPlaceOrderEmptyDishesThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.placeOrder(customer, List.of()));
    }
}
