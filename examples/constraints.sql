

CREATE TABLE albums_bak (
    AlbumId  INTEGER PRIMARY KEY,
    Title    NVARCHAR(160),
    ArtistId INTEGER
);

CREATE TABLE albums_bak(
    AlbumId  INTEGER,
    Title    NVARCHAR(160),
    ArtistId INTEGER,
    PRIMARY KEY (AlbumId, Title)
);

CREATE TABLE albums_bak (
    AlbumId  INTEGER NOT NULL PRIMARY KEY,
    Title    NVARCHAR(160) NOT NULL,
    ArtistId INTEGER
);

CREATE TABLE albums_bak (
    AlbumId  INTEGER NOT NULL PRIMARY KEY,
    Title    NVARCHAR(160) NOT NULL,
    ArtistId INTEGER
) WITHOUT ROWID ;

CREATE TABLE albums_bak
(
    AlbumId  INTEGER       NOT NULL PRIMARY KEY,
    Title    NVARCHAR(160) NOT NULL,
    ArtistId INTEGER,
    FOREIGN KEY (ArtistId)
        REFERENCES artists (ArtistId)
) ;

INSERT INTO albums_bak (Title, ArtistId)
VALUES ("Foo", -100);

PRAGMA foreign_keys = ON;


CREATE TABLE albums_bak
(
    AlbumId  INTEGER       NOT NULL PRIMARY KEY,
    Title    NVARCHAR(160) NOT NULL,
    ArtistId INTEGER,
    FOREIGN KEY (ArtistId)
        REFERENCES artists (ArtistId)
        ON DELETE SET NULL
) ;

