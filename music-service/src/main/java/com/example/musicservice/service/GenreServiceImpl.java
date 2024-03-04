package com.example.musicservice.service;

import com.example.musicservice.model.Genre;
import com.example.musicservice.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    @Override
    public Genre create(Genre genre) {


        return genreRepository.save(Genre.builder().name(genre.getName()).key(genre.getKey()).build());
    }


    @Override
    public void addArtistToGenre(String genreId, String artistId) {

        genreRepository.addArtistToGenre(genreId,artistId, LocalDateTime.now());

    }

    @Override
    public void removeArtistFromGenre(String genreId, String artistId) {

        genreRepository.removeArtistFromGenre(genreId,artistId);
    }

    @Override
    public void addSongToGenre(String genreId, String songId) {

        genreRepository.addSongToGenre(genreId,songId,LocalDateTime.now());
    }

    @Override
    public void removeSongFromGenre(String genreId, String songId) {
        genreRepository.removeSongFromGenre(genreId,songId);
    }
}
