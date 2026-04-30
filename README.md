# Restaurant Ordering System

Java-система управління замовленнями ресторану, побудована з використанням патернів проектування GoF та принципів SOLID.

## Що реалізовано

- Меню страв із можливістю додавання та пошуку
- Замовлення клієнтів із підрахунком суми
- Сповіщення кухні про нові замовлення через Observer
- Єдина база замовлень через Singleton
- Створення різних типів замовлень через Factory

## Патерни проектування

**Singleton — `OrderDatabase`**  
Єдина база замовлень у всьому додатку. Гарантує, що всі компоненти працюють з одним і тим самим списком замовлень.

**Factory — `OrderFactory`**  
Інтерфейс для створення замовлень. `RegularOrderFactory` створює звичайні замовлення, `BulkOrderFactory` — оптові. Додавання нового типу не змінює існуючий код.

**Observer — `KitchenNotifier`**  
Сповіщає підписників про нові замовлення. `RestaurantService` не знає, хто конкретно отримає сповіщення — він лише викликає `notifyObservers()`.

## Структура

```
src/main/java/com/restaurant/
├── model/          — Dish, Menu, Customer, Order, OrderType, OrderStatus
├── factory/        — OrderFactory, RegularOrderFactory, BulkOrderFactory
├── observer/       — OrderObserver, KitchenNotifier, ConsoleKitchenObserver
├── singleton/      — OrderDatabase
└── service/        — RestaurantService

diagrams/           — UML-діаграми (.puml + .png)
```

## Запуск тестів

```bash
mvn test
```

```
Tests run: 62, Failures: 0, Errors: 0, Skipped: 0 — BUILD SUCCESS
```

## UML-діаграми

| Файл | Опис |
|---|---|
| `diagrams/class_diagram.png` | Загальна діаграма класів |
| `diagrams/singleton_diagram.png` | Патерн Singleton |
| `diagrams/factory_diagram.png` | Патерн Factory |
| `diagrams/observer_diagram.png` | Патерн Observer |

Для повторної генерації:
```bash
plantuml diagrams/*.puml
```
