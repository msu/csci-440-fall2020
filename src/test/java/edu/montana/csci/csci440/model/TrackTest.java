package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import edu.montana.csci.csci440.util.DB;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrackTest extends DBTest {

    @Test
    void testAllLoadsAllAlbums() {
        List<Track> all = Track.all();
        assertEquals(3503, all.size());
    }

    @Test
    void testPagingWorks() {
        assertEquals(1000, Track.all(1, 1000).size());
        assertEquals(1000, Track.all(2, 1000).size());
        assertEquals(1000, Track.all(3, 1000).size());
        assertEquals(503, Track.all(4, 1000).size());
        assertEquals(0, Track.all(5, 1000).size());
    }

    @Test
    void testCreateWorks() {
        Track track = new Track();

        track.setName("Example");
        track.setAlbum(Album.find(1));

        assertNull(track.getTrackId());
        track.create();
        assertNotNull(track.getTrackId());

        assertEquals(Album.find(1), track.getAlbum());
    }

    @Test
    void testValidationWorks() {
        Track track = new Track();

        assertFalse(track.verify());
        // expect a name and album
        assertEquals(2, track.getErrors().size());

        track.setName("Example");
        track.setAlbum(Album.find(1));

        assertTrue(track.verify());
        assertEquals(0, track.getErrors().size());
    }

    @Test
    void testUpdateWorks() {
        Track track = Track.find(1);
        String newTitle = "A New Name";
        track.setName(newTitle);
        track.update();
        assertEquals(newTitle, Track.find(1).getName());
    }

    @Test
    void testArtistNameIsStoredOnTrack() {
        Track track = Track.find(1);
        long connectionCount = DB.getConnectionCount();
        String artistName = track.getArtistName();
        assertEquals("AC/DC", artistName);
        assertEquals(connectionCount, DB.getConnectionCount());
    }

    @Test
    void testAlbumNameIsStoredOnTrack() {
        Track track = Track.find(1);
        long connectionCount = DB.getConnectionCount();
        String albumTitle = track.getAlbumTitle();
        assertEquals("For Those About To Rock We Salute You", albumTitle);
        assertEquals(connectionCount, DB.getConnectionCount());
    }

    @Test
    void testOrderByWorks() {
        Track track = Track.all(1, 1, "Milliseconds").get(0);
        assertEquals("É Uma Partida De Futebol", track.getName());
    }

    @Test
    void testTrackListsWorks() {
        Track track = Track.find(1);
        assertEquals(3, track.getPlaylists().size());
    }

    @Test
    void trackCountIsCachedInRedis(){

        long initialCount = DB.getConnectionCount();
        Long count = Track.count();
        assertEquals(initialCount + 1, DB.getConnectionCount());

        // should be cached for second time
        count = Track.count();
        assertEquals(initialCount + 1, DB.getConnectionCount());

        Track track = new Track();
        track.setName("Example");
        track.setAlbum(Album.find(1));
        assertTrue(track.create());

        // cache should have been invalidated
        count = Track.count();
        assertEquals(initialCount + 2, DB.getConnectionCount());

    }

}
