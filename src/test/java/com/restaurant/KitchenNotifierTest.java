package com.restaurant;

import com.restaurant.model.*;
import com.restaurant.observer.ConsoleKitchenObserver;
import com.restaurant.observer.KitchenNotifier;
import com.restaurant.observer.OrderObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KitchenNotifierTest {

    private KitchenNotifier notifier;
    private ConsoleKitchenObserver observer;
    private Order order;

    @BeforeEach
    void setUp() {
        notifier = new KitchenNotifier();
        observer = new ConsoleKitchenObserver();
        Customer customer = new Customer(1, "Іван");
        List<Dish> dishes = List.of(new Dish("Піца", 150.0));
        order = new Order(1, customer, dishes, OrderType.REGULAR);
    }

    @Test
    void testSubscribeIncreasesCount() {
        notifier.subscribe(observer);
        assertEquals(1, notifier.getObserverCount());
    }

    @Test
    void testUnsubscribeDecreasesCount() {
        notifier.subscribe(observer);
        notifier.unsubscribe(observer);
        assertEquals(0, notifier.getObserverCount());
    }

    @Test
    void testNotifyCallsObserver() {
        notifier.subscribe(observer);
        notifier.notifyObservers(order);
        assertTrue(observer.hasReceived(order));
    }

    @Test
    void testNotifyMultipleObservers() {
        ConsoleKitchenObserver obs2 = new ConsoleKitchenObserver();
        notifier.subscribe(observer);
        notifier.subscribe(obs2);
        notifier.notifyObservers(order);
        assertTrue(observer.hasReceived(order));
        assertTrue(obs2.hasReceived(order));
    }

    @Test
    void testNoObserversNoException() {
        assertDoesNotThrow(() -> notifier.notifyObservers(order));
    }

    @Test
    void testSubscribeNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> notifier.subscribe(null));
    }

    @Test
    void testObserverReceivesCorrectOrder() {
        notifier.subscribe(observer);
        notifier.notifyObservers(order);
        assertEquals(1, observer.getReceivedOrders().size());
        assertEquals(order, observer.getReceivedOrders().get(0));
    }

    @Test
    void testObserverReceivesMultipleOrders() {
        notifier.subscribe(observer);
        Customer c2 = new Customer(2, "Марія");
        Order order2 = new Order(2, c2, List.of(new Dish("Суші", 200.0)), OrderType.BULK);
        notifier.notifyObservers(order);
        notifier.notifyObservers(order2);
        assertEquals(2, observer.getReceivedOrders().size());
    }

    @Test
    void testUnsubscribedObserverNotNotified() {
        notifier.subscribe(observer);
        notifier.unsubscribe(observer);
        notifier.notifyObservers(order);
        assertFalse(observer.hasReceived(order));
    }
}
