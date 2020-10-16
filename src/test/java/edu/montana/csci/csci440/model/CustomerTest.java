package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest extends DBTest {

    @Test
    void testAllLoadsAllCustomers() {
        List<Customer> all = Customer.all();
        assertEquals(59, all.size());
    }

    @Test
    void testCustomerFieldsLoad() {
        Customer c = Customer.find(1);
        assertEquals("luisg@embraer.com.br", c.getEmail());
    }

    @Test
    void testPagingWorks() {
        assertEquals(25, Customer.all(0, 25).size());
        assertEquals(25, Customer.all(2, 25).size());
        assertEquals(9, Customer.all(3, 25).size());
        assertEquals(0, Customer.all(4, 25).size());
        assertEquals(0, Customer.all(5, 25).size());
        assertEquals(0, Customer.all(42, 25).size());
    }

}
