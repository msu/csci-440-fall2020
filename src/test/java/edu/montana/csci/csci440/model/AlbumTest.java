package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest extends DBTest {

    @Test
    void testAllLoadsAllAlbums() {
        List<Album> all = Album.all();
        assertEquals(347, all.size());
    }

    @Test
    void testPagingWorks() {
        assertEquals(100, Album.all(1, 100).size());
        assertEquals(100, Album.all(2, 100).size());
        assertEquals(100, Album.all(3, 100).size());
        assertEquals(47, Album.all(4, 100).size());
        assertEquals(0, Album.all(5, 100).size());
    }

    @Test
    void testCreateWorks() {
        Album album = new Album();

        album.setTitle("Example");
        album.setArtist(Artist.find(1));

        assertNull(album.getAlbumId());
        album.create();
        assertNotNull(album.getAlbumId());

        assertEquals(Artist.find(1), album.getArtist());
    }

    @Test
    void testValidationWorks() {
        Album album = new Album();

        assertFalse(album.verify());
        // expect a title and artist
        assertEquals(2, album.getErrors().size());

        album.setTitle("Example");
        album.setArtist(Artist.find(1));
        assertTrue(album.verify());
        assertEquals(0, album.getErrors().size());
    }

    @Test
    void testUpdateWorks() {
        Album album = Album.find(1);
        String newTitle = "A New Title";
        album.setTitle(newTitle);
        album.update();
        assertEquals(newTitle, Album.find(1).getTitle());
    }

}
