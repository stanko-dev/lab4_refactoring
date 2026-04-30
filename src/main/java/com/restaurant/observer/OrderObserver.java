package com.restaurant.observer;

import com.restaurant.model.Order;

public interface OrderObserver {
    void onOrderPlaced(Order order);
}
