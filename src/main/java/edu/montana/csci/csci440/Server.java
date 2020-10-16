package edu.montana.csci.csci440;

import edu.montana.csci.csci440.controller.*;
import edu.montana.csci.csci440.model.Employee;
import edu.montana.csci.csci440.model.Track;
import edu.montana.csci.csci440.util.Web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static spark.Spark.*;

class Server {

    public static void main(String[] args) {

        /* ========================================================================= */
        /* Poor Mans Rails Implementation                                            */
        /* ========================================================================= */
        Web.init();

        /* ========================================================================= */
        /* Root Path                                                                 */
        /* ========================================================================= */
        get("/", (req, resp) -> {
            Web.message("SQL Is Awesome");
            return Web.renderTemplate("templates/index.vm");
        });

        /* ========================================================================= */
        /* Music
        /* ========================================================================= */
        ArtistController.init();
        AlbumsController.init();
        TracksController.init();
        PlaylistsController.init();

        /* ========================================================================= */
        /* Business
        /* ========================================================================= */
        EmployeesController.init();
        CustomersController.init();
        InvoicesController.init();


    }

}