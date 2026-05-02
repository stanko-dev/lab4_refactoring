package com.restaurant.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final int id;
    private final Customer customer;
    private final List<Dish> dishes;
    private final OrderType type;
    private OrderStatus status;

    public Order(int id, Customer customer, List<Dish> dishes, OrderType type) {
        if (customer == null) throw new IllegalArgumentException("Customer is required");
        if (dishes == null || dishes.isEmpty()) throw new IllegalArgumentException("Order must have at least one dish");
        this.id = id;
        this.customer = customer;
        this.dishes = new ArrayList<>(dishes);
        this.type = type;
        this.status = OrderStatus.PENDING;
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<Dish> getDishes() { return Collections.unmodifiableList(dishes); }
    public OrderType getType() { return type; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public double getTotalPrice() {
        double total = dishes.stream().mapToDouble(Dish::getPrice).sum();
        if (type == OrderType.BULK) {
            return Math.round(total * 0.9 * 100.0) / 100.0;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customer=" + customer.getName() + ", type=" + type + ", status=" + status + "}";
    }
}
