package com.restaurant.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final List<Dish> dishes = new ArrayList<>();

    public void addDish(Dish dish) {
        if (dish == null) throw new IllegalArgumentException("Dish cannot be null");
        dishes.add(dish);
    }

    public boolean containsDish(Dish dish) {
        return dishes.contains(dish);
    }

    public List<Dish> getDishes() {
        return Collections.unmodifiableList(dishes);
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public int size() {
        return dishes.size();
    }
}
