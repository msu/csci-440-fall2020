package edu.montana.csci.csci440.controller;

import edu.montana.csci.csci440.helpers.EmployeeHelper;
import edu.montana.csci.csci440.model.Employee;
import edu.montana.csci.csci440.util.Web;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class EmployeesController {
    public static void init(){
        /* CREATE */
        get("/employees/new", (req, resp) -> {
            Employee employee = new Employee();
            return Web.renderTemplate("templates/employees/new.vm", "employee", employee);
        });

        post("/employees/new", (req, resp) -> {
            Employee emp = new Employee();
            Web.putValuesInto(emp, "FirstName", "LastName");
            if (emp.create()) {
                Web.message("Created An Employee!");
                return Web.redirect("/employees/" + emp.getEmployeeId());
            } else {
                Web.error("Could Not Create An Employee!");
                return Web.renderTemplate("templates/employees/new.vm",
                        "employee", emp);
            }
        });

        /* READ */
        get("/employees", (req, resp) -> {
            List<Employee> employees = Employee.all(1, Web.PAGE_SIZE);
            return Web.renderTemplate("templates/employees/index.vm",
                    "employees", employees);
        });

        get("/employees/tree", (request, response) -> {
            String employeeTree = EmployeeHelper.makeEmployeeTree();
            return Web.renderTemplate("templates/employees/tree.vm",
                    "employeeTree", employeeTree);
        });

        get("/employees/sales", (request, response) -> {
            List<Employee.SalesSummary> salesInfo = Employee.getSalesSummaries();
            return Web.renderTemplate("templates/employees/sales.vm",
                    "salesInfo", salesInfo);
        });

        get("/employees/:id", (req, resp) -> {
            Employee employee = Employee.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/employees/show.vm",
                    "employee", employee);
        });

        /* UPDATE */
        get("/employees/:id/edit", (req, resp) -> {
            Employee employee = Employee.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/employees/edit.vm",
                    "employee", employee);
        });

        post("/employees/:id", (req, resp) -> {
            Employee emp = Employee.find(Integer.parseInt(req.params(":id")));
            Web.putValuesInto(emp, "FirstName", "LastName");
            if (emp.update()) {
                Web.message("Updated Employee!");
                return Web.redirect("/employees/" + emp.getEmployeeId());
            } else {
                Web.error("Could Not Update Employee!");
                return Web.renderTemplate("templates/employees/edit.vm",
                        "employee", emp);
            }
        });

        /* DELETE */
        get("/employees/:id/delete", (req, resp) -> {
            Employee employee = Employee.find(Integer.parseInt(req.params(":id")));
            employee.delete();
            Web.message("Deleted Employee " + employee.getEmail());
            return Web.redirect("/employees");
        });
    }
}
