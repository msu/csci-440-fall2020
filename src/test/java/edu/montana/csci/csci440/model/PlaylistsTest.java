package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistsTest extends DBTest {

    @Test
    void testAllLoadsAllPlaylists() {
        List<Playlist> all = Playlist.all();
        assertEquals(18, all.size());
    }

    @Test
    void testPlaylistFieldsLoad() {
        Playlist c = Playlist.find(3);
        assertEquals("TV Shows", c.getName());
        assertEquals(213, c.getTracks().size());
        assertEquals("...And Found", c.getTracks().get(1).getName());
    }

    @Test
    void testPagingWorks() {
        assertEquals(5, Playlist.all(0, 5).size());
        assertEquals(10, Playlist.all(1, 10).size());
        assertEquals(8, Playlist.all(2, 10).size());
        assertEquals(0, Playlist.all(42, 10).size());
    }

}
