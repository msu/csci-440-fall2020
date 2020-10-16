SELECT 1 + 1;

select 1 + 1;

SELECT TrackId,
       Name,
       Composer,
       UnitPrice
FROM tracks;

SELECT *
FROM tracks;

SELECT name
FROM tracks
WHERE Milliseconds > 3 * 60 * 1000;

SELECT name, AlbumId
FROM tracks
WHERE AlbumId = 1;

SELECT Title
FROM albums
WHERE   AlbumId = 1;

SELECT name, Milliseconds, AlbumId
FROM tracks
WHERE AlbumId = 1 OR Milliseconds > 3 * 60 * 1000;

SELECT name, Milliseconds, AlbumId
FROM tracks
WHERE NOT AlbumId = 1 AND Milliseconds > 3 * 60 * 1000;

select Name from tracks where Composer IS NULL;



