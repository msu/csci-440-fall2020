package edu.montana.csci.csci440.controller;

import edu.montana.csci.csci440.model.Artist;
import edu.montana.csci.csci440.model.Track;
import edu.montana.csci.csci440.util.Web;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class ArtistController {
    public static void init() {
        /* CREATE */
        get("/artists/new", (req, resp) -> {
            Artist artist = new Artist();
            return Web.renderTemplate("templates/artists/new.vm", "artist", artist);
        });

        post("/artists/new", (req, resp) -> {
            Artist artist = new Artist();
            Web.putValuesInto(artist, "Name");
            if (artist.create()) {
                Web.message("Created An Artist!");
                return Web.redirect("/artists/" + artist.getArtistId());
            } else {
                Web.error("Could Not Create An Artist!");
                return Web.renderTemplate("templates/artists/new.vm",
                        "artist", artist);
            }
        });

        /* READ */
        get("/artists", (req, resp) -> {
            List<Artist> artists = Artist.all(Web.getPage(), Web.PAGE_SIZE);
            return Web.renderTemplate("templates/artists/index.vm",
                    "artists", artists);
        });

        get("/artists/:id", (req, resp) -> {
            Artist artist = Artist.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/artists/show.vm",
                    "artist", artist);
        });

        /* UPDATE */
        get("/artists/:id/edit", (req, resp) -> {
            Artist artist = Artist.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/artists/edit.vm",
                    "artist", artist);
        });

        post("/artists/:id", (req, resp) -> {
            Artist artist = Artist.find(Integer.parseInt(req.params(":id")));
            Web.putValuesInto(artist, "Name");
            if (artist.update()) {
                Web.message("Updated Artist!");
                return Web.redirect("/artists/" + artist.getArtistId());
            } else {
                Web.error("Could Not Update Artist!");
                return Web.renderTemplate("templates/artists/edit.vm",
                        "artist", artist);
            }
        });

        /* DELETE */
        get("/artists/:id/delete", (req, resp) -> {
            Artist artist = Artist.find(Integer.parseInt(req.params(":id")));
            artist.delete();
            Web.message("Deleted Artist " + artist.getName());
            return Web.redirect("/artists");
        });
    }
}
