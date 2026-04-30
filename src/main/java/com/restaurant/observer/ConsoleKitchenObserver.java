package com.restaurant.observer;

import com.restaurant.model.Order;
import java.util.ArrayList;
import java.util.List;

public class ConsoleKitchenObserver implements OrderObserver {
    private final List<Order> receivedOrders = new ArrayList<>();

    @Override
    public void onOrderPlaced(Order order) {
        receivedOrders.add(order);
        System.out.println("[Kitchen] Нове замовлення #" + order.getId()
                + " від " + order.getCustomer().getName()
                + " (" + order.getType() + ")");
    }

    public List<Order> getReceivedOrders() {
        return receivedOrders;
    }

    public boolean hasReceived(Order order) {
        return receivedOrders.contains(order);
    }
}
