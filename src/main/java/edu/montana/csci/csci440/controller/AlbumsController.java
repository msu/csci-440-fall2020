package edu.montana.csci.csci440.controller;

import edu.montana.csci.csci440.model.Album;
import edu.montana.csci.csci440.util.Web;

import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class AlbumsController {
    public static void init(){
        /* CREATE */
        get("/albums/new", (req, resp) -> {
            Album album = new Album();
            return Web.renderTemplate("templates/albums/new.vm", "album", album);
        });

        post("/albums/new", (req, resp) -> {
            Album album = new Album();
            Web.putValuesInto(album, "Title");
            if (album.create()) {
                Web.message("Created A Album!");
                return Web.redirect("/albums/" + album.getAlbumId());
            } else {
                Web.error("Could Not Create A Album!");
                return Web.renderTemplate("templates/albums/new.vm",
                        "album", album);
            }
        });

        /* READ */
        get("/albums", (req, resp) -> {
            List<Album> albums = Album.all(Web.getPage(), Web.PAGE_SIZE);
            return Web.renderTemplate("templates/albums/index.vm",
                    "albums", albums);
        });

        get("/albums/:id", (req, resp) -> {
            Album album = Album.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/albums/show.vm",
                    "album", album);
        });

        /* UPDATE */
        get("/albums/:id/edit", (req, resp) -> {
            Album album = Album.find(Integer.parseInt(req.params(":id")));
            return Web.renderTemplate("templates/albums/edit.vm",
                    "album", album);
        });

        post("/albums/:id", (req, resp) -> {
            Album album = Album.find(Integer.parseInt(req.params(":id")));
            Web.putValuesInto(album, "Title");
            if (album.update()) {
                Web.message("Updated Album!");
                return Web.redirect("/albums/" + album.getAlbumId());
            } else {
                Web.error("Could Not Update Album!");
                return Web.renderTemplate("templates/albums/edit.vm",
                        "album", album);
            }
        });

        /* DELETE */
        get("/albums/:id/delete", (req, resp) -> {
            Album album = Album.find(Integer.parseInt(req.params(":id")));
            album.delete();
            Web.message("Deleted Album " + album.getTitle());
            return Web.redirect("/albums");
        });
    }
}
