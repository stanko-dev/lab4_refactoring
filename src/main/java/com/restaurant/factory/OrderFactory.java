package com.restaurant.factory;

import com.restaurant.model.Customer;
import com.restaurant.model.Dish;
import com.restaurant.model.Order;
import java.util.List;

public interface OrderFactory {
    Order createOrder(int id, Customer customer, List<Dish> dishes);
}
