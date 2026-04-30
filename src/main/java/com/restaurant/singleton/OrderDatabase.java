package com.restaurant.singleton;

import com.restaurant.model.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDatabase {
    private static OrderDatabase instance;
    private final List<Order> orders = new ArrayList<>();

    private OrderDatabase() {}

    public static synchronized OrderDatabase getInstance() {
        if (instance == null) {
            instance = new OrderDatabase();
        }
        return instance;
    }

    public void saveOrder(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int getOrderCount() {
        return orders.size();
    }

    public void clear() {
        orders.clear();
    }

    // Лише для тестування — скидання екземпляра
    static synchronized void resetInstance() {
        instance = null;
    }
}
