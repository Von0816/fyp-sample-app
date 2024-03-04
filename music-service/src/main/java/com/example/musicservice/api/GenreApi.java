package com.example.musicservice.api;


import com.example.musicservice.dto.UserRegistrationRequestRecord;
import com.example.musicservice.model.Genre;
import com.example.musicservice.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreApi {

    private final GenreService genreService;


    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody Genre genre){

        genreService.create(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{genreId}/artist/{artistId}/assign-artist")
    public void addArtistToGenre(@PathVariable String genreId, @PathVariable String artistId) {

        genreService.addArtistToGenre(genreId,artistId);
    }
    @PutMapping("/{genreId}/song/{songId}/assign-song")
    public void addSongToGenre(@PathVariable String genreId, @PathVariable String songId) {

        genreService.addSongToGenre(genreId,songId);
    }

    @PutMapping("/{genreId}/artist/{artistId}/un-assign-artist")
    public void removeArtistFromGenre(@PathVariable String genreId, @PathVariable String artistId) {

        genreService.removeArtistFromGenre(genreId,artistId);
    }
    @PutMapping("/{genreId}/song/{songId}/un-assign-song")
    public void removeSongFromGenre(@PathVariable String genreId, @PathVariable String songId) {

        genreService.removeSongFromGenre(genreId,songId);
    }

}
