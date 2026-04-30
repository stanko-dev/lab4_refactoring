package com.restaurant.factory;

import com.restaurant.model.Customer;
import com.restaurant.model.Dish;
import com.restaurant.model.Order;
import com.restaurant.model.OrderType;
import java.util.List;

public class RegularOrderFactory implements OrderFactory {
    @Override
    public Order createOrder(int id, Customer customer, List<Dish> dishes) {
        return new Order(id, customer, dishes, OrderType.REGULAR);
    }
}
