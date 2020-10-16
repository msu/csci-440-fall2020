package edu.montana.csci.csci440.controller;

import edu.montana.csci.csci440.model.Invoice;
import edu.montana.csci.csci440.util.Web;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class InvoicesController {
    public static void init(){
        /* READ */
        get("/invoices", (req, resp) -> {
            List<Invoice> invoices = Invoice.all(Web.getPage(), Web.PAGE_SIZE);
            return Web.renderTemplate("templates/invoices/index.vm",
                    "invoices", invoices);
        });

        get("/invoices/:id", (req, resp) -> {
            Invoice invoice = Invoice.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/invoices/show.vm",
                    "invoice", invoice);
        });
    }
}
