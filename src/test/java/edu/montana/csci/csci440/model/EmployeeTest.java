package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import edu.montana.csci.csci440.helpers.EmployeeHelper;
import edu.montana.csci.csci440.util.DB;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest extends DBTest {

    @Test
    void testAllLoadsAllEmployees() {
        List<Employee> all = Employee.all();
        assertEquals(8, all.size());
    }

    @Test
    void testPagingWorks() {
        assertEquals(2, Employee.all(0, 2).size());
        assertEquals(2, Employee.all(2, 2).size());
        assertEquals(2, Employee.all(3, 2).size());
        assertEquals(2, Employee.all(4, 2).size());
        assertEquals(0, Employee.all(5, 2).size());
        assertEquals(0, Employee.all(42, 2).size());
    }

    @Test
    void testCreateWorks() {
        Employee emp = new Employee();

        emp.setLastName("Blow");
        emp.setFirstName("Joe");
        emp.setTitle("Programmer");
        emp.setEmail("demo@test.com");
        emp.setReportsTo(Employee.find(1));

        assertNull(emp.getEmployeeId());
        emp.create();
        assertNotNull(emp.getEmployeeId());

        assertEquals(Employee.find(1), emp.getBoss());
    }

    @Test
    void testValidationWorks() {
        Employee emp = new Employee();
        assertFalse(emp.verify());
        // expect a first name, last name and valid email
        assertEquals(3, emp.getErrors().size());

        emp.setFirstName("Joe");
        emp.setLastName("Blow");
        assertFalse(emp.verify());
        // expect a valid email
        assertEquals(1, emp.getErrors().size());

        emp.setEmail("invalid_email.com");
        assertFalse(emp.verify());
        // expect a valid email with an '@' in it
        assertEquals(1, emp.getErrors().size());

        emp.setEmail("foo@bar.com");
        assertTrue(emp.verify());
        assertEquals(0, emp.getErrors().size());
    }

    @Test
    void testUpdateWorks() {
        Employee emp = Employee.find(1);
        String newEmailAddress = "aNewEmailAddress@test.com";
        emp.setEmail(newEmailAddress);
        emp.update();

        Employee found = Employee.findByEmail(newEmailAddress);
        assertEquals(emp.getEmployeeId(), found.getEmployeeId());
    }

    @Test
    void testEmployeeHelperOnlyIssuesOneQuery(){
        long connectionCount = DB.getConnectionCount();
        String str = EmployeeHelper.makeEmployeeTree();
        assertNotNull(str);
        assertEquals(1, DB.getConnectionCount() - connectionCount );
    }

    @Test
    void testEmployeeSalesReport() {
        List<Employee.SalesSummary> salesSummaries = Employee.getSalesSummaries();
        assertEquals("jane@chinookcorp.com", salesSummaries.get(0).getEmail());
        assertEquals(146, salesSummaries.get(0).getSalesCount());
        assertEquals(833.04, salesSummaries.get(0).getSalesTotals());
    }

}
