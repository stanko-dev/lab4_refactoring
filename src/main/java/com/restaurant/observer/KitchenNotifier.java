package com.restaurant.observer;

import com.restaurant.model.Order;
import java.util.ArrayList;
import java.util.List;

public class KitchenNotifier {
    private final List<OrderObserver> observers = new ArrayList<>();

    public void subscribe(OrderObserver observer) {
        if (observer == null) throw new IllegalArgumentException("Observer cannot be null");
        observers.add(observer);
    }

    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(order);
        }
    }

    public int getObserverCount() {
        return observers.size();
    }
}
