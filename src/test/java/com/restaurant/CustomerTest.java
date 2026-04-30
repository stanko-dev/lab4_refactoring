package com.restaurant;

import com.restaurant.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerCreation() {
        Customer customer = new Customer(1, "Іван Петренко");
        assertNotNull(customer);
    }

    @Test
    void testCustomerGetId() {
        Customer customer = new Customer(42, "Марія");
        assertEquals(42, customer.getId());
    }

    @Test
    void testCustomerGetName() {
        Customer customer = new Customer(1, "Олена");
        assertEquals("Олена", customer.getName());
    }

    @Test
    void testCustomerNullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(1, null));
    }

    @Test
    void testCustomerBlankNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(1, ""));
    }
}
