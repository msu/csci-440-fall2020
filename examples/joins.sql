
-- Inner join
SELECT tracks.name as TrackName, albums.title as AlbumTitle
FROM tracks
JOIN albums on tracks.AlbumId = albums.AlbumId
WHERE albums.Title = "Machine Head";


-- Outer join
SELECT name as ArtistName, Title as AlbumTitle
FROM artists
         LEFT OUTER JOIN albums on artists.ArtistId = albums.ArtistId;


-- Self join
SELECT employees.FirstName as FirstName,
       employees.EmployeeId as EmployeeId,
       bosses.FirstName as BossFirstName,
       bosses.EmployeeId as BossEmployeeId
FROM employees
JOIN employees AS bosses ON employees.ReportsTo = bosses.EmployeeId;


SELECT *
FROM albums
    CROSS JOIN artists;

SELECT
    tracks.Name as TrackName, Title, artists.Name as ArtistsName
FROM
     tracks
    JOIN albums
     ON tracks.AlbumId = albums.AlbumId
    JOIN artists
     ON albums.ArtistId = artists.ArtistId
WHERE artists.name = "AC/DC";


