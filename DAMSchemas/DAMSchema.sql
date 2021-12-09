--
-- Application Schema SQL DDL Script
-- Generated with Matisse Schema Definition Language 9.1.12 
--
-- Generation date: Thu Dec 09 14:10:36 2021
-- Generated from database 'DAM@DESKTOP-4PC1QD0'
--


CREATE NAMESPACE IF NOT EXISTS TrackList;

SET CURRENT_NAMESPACE TrackList;

--
-- Classes Definitions
--

CREATE TABLE IF NOT EXISTS track (
  TrackURI STRING,
  TrackName STRING,
  ArtistURIs STRING,
  ArtistNames STRING,
  AlbumURI STRING,
  AlbumName STRING,
  AlbumArtistURIs STRING,
  AlbumArtistNames STRING,
  AlbumReleaseDate STRING,
  AlbumImageURL STRING,
  DiscNumber STRING,
  TrackNumber STRING,
  TrackDuration STRING,
  TrackPreviewURL STRING,
  Explicit BOOLEAN,
  Popularity STRING,
  AddedBy STRING,
  AddedAt STRING
);

--
-- Commit the Application Schema Updates
--
COMMIT;

