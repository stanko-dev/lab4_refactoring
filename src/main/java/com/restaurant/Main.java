package com.restaurant;

import com.restaurant.factory.BulkOrderFactory;
import com.restaurant.factory.RegularOrderFactory;
import com.restaurant.model.Customer;
import com.restaurant.model.Dish;
import com.restaurant.model.Menu;
import com.restaurant.model.Order;
import com.restaurant.observer.ConsoleKitchenObserver;
import com.restaurant.observer.KitchenNotifier;
import com.restaurant.service.RestaurantService;
import com.restaurant.singleton.OrderDatabase;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Система управління замовленнями ресторану ===\n");

        System.out.println("1. Формування меню:");
        Menu menu = new Menu();
        Dish pizza = new Dish("Піца Маргарита", 180.0);
        Dish soup = new Dish("Борщ", 95.0);
        Dish juice = new Dish("Апельсиновий сік", 50.0);
        menu.addDish(pizza);
        menu.addDish(soup);
        menu.addDish(juice);
        menu.getDishes().forEach(d -> System.out.println("  - " + d));
        System.out.println();

        System.out.println("2. Singleton — OrderDatabase:");
        OrderDatabase db1 = OrderDatabase.getInstance();
        OrderDatabase db2 = OrderDatabase.getInstance();
        System.out.println("  db1 == db2: " + (db1 == db2));
        System.out.println();

        System.out.println("3. Підключення кухні (Observer):");
        KitchenNotifier notifier = new KitchenNotifier();
        ConsoleKitchenObserver kitchen = new ConsoleKitchenObserver();
        notifier.subscribe(kitchen);
        System.out.println("  Кухню підписано на нові замовлення.");
        System.out.println();

        System.out.println("4. Розміщення замовлень (Factory):");
        Customer c1 = new Customer(1, "Іван Петренко");
        Customer c2 = new Customer(2, "Марія Коваленко");

        RestaurantService regularService = new RestaurantService(
                new RegularOrderFactory(), notifier, OrderDatabase.getInstance());
        RestaurantService bulkService = new RestaurantService(
                new BulkOrderFactory(), notifier, OrderDatabase.getInstance());

        Order order1 = regularService.placeOrder(c1, List.of(pizza, juice));
        System.out.println("  Звичайне замовлення: сума = " + order1.getTotalPrice() + " грн");

        Order order2 = bulkService.placeOrder(c2, List.of(pizza, pizza, soup, juice));
        System.out.println("  Оптове замовлення (-10%): сума = " + order2.getTotalPrice() + " грн");
        System.out.println();

        System.out.println("5. Стан бази замовлень:");
        System.out.println("  Всього замовлень: " + OrderDatabase.getInstance().getOrderCount());
        System.out.println("  Кухня отримала: " + kitchen.getReceivedOrders().size() + " замовлень");
    }
}
