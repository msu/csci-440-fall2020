package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoicesTest extends DBTest {

    @Test
    void testAllLoadsAllInvoices() {
        List<Invoice> all = Invoice.all();
        assertEquals(412, all.size());
    }

    @Test
    void testInvoiceFieldsLoad() {
        Invoice c = Invoice.find(1);
        assertEquals("Theodor-Heuss-Straße 34", c.getBillingAddress());
        assertEquals("Stuttgart", c.getBillingCity());
    }

    @Test
    void testPagingWorks() {
        assertEquals(5, Invoice.all(0, 5).size());
        assertEquals(100, Invoice.all(0, 100).size());
        assertEquals(100, Invoice.all(2, 100).size());
        assertEquals(100, Invoice.all(3, 100).size());
        assertEquals(100, Invoice.all(4, 100).size());
        assertEquals(12, Invoice.all(5, 100).size());
        assertEquals(0, Invoice.all(42, 100).size());
    }

}
