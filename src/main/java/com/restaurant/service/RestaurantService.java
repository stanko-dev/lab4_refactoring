package com.restaurant.service;

import com.restaurant.factory.OrderFactory;
import com.restaurant.model.Customer;
import com.restaurant.model.Dish;
import com.restaurant.model.Order;
import com.restaurant.observer.KitchenNotifier;
import com.restaurant.singleton.OrderDatabase;
import java.util.List;

public class RestaurantService {
    private final OrderFactory orderFactory;
    private final KitchenNotifier kitchenNotifier;
    private final OrderDatabase orderDatabase;
    private int nextOrderId = 1;

    public RestaurantService(OrderFactory orderFactory,
                             KitchenNotifier kitchenNotifier,
                             OrderDatabase orderDatabase) {
        this.orderFactory = orderFactory;
        this.kitchenNotifier = kitchenNotifier;
        this.orderDatabase = orderDatabase;
    }

    public Order placeOrder(Customer customer, List<Dish> dishes) {
        if (customer == null) throw new IllegalArgumentException("Customer is required");
        if (dishes == null || dishes.isEmpty()) throw new IllegalArgumentException("Dishes list cannot be empty");
        Order order = orderFactory.createOrder(nextOrderId++, customer, dishes);
        orderDatabase.saveOrder(order);
        kitchenNotifier.notifyObservers(order);
        return order;
    }

    public int getNextOrderId() {
        return nextOrderId;
    }
}
