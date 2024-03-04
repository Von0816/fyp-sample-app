package com.example.musicservice.service;

import com.example.musicservice.model.Artist;

public interface ArtistService {

    Artist createNew(Artist artist);
    Artist findById(String id);
    void deleteById(String id);

    void addArtistAndYearRelationship(String artistId, Integer integer, String id,String genreId);
}
