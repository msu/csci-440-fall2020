package edu.montana.csci.csci440.controller;

import edu.montana.csci.csci440.model.Customer;
import edu.montana.csci.csci440.util.Web;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class CustomersController {
    public static void init(){
        /* READ */
        get("/customers", (req, resp) -> {
            List<Customer> customers = Customer.all(Web.getPage(), Web.PAGE_SIZE);
            return Web.renderTemplate("templates/customers/index.vm",
                    "customers", customers);
        });

        get("/customers/:id", (req, resp) -> {
            Customer customer = Customer.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/customers/show.vm",
                    "customer", customer);
        });
    }
}
