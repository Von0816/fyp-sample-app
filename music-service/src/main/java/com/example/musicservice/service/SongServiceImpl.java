package com.example.musicservice.service;

import com.example.musicservice.dto.SongRecord;
import com.example.musicservice.model.Song;
import com.example.musicservice.model.Status;
import com.example.musicservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{

    private final SongRepository repository;
    private final YearService yearService;
    private final ArtistService artistService;
    @Override
    public Song create(SongRecord songRecord, String artistId) {


        Song song = Song.builder()
                .name(songRecord.title())
                .storageId(songRecord.storageId())
                .storageType(songRecord.storageType())
                .type(songRecord.songType())
                .duration(songRecord.duration())
                .releasedDate(songRecord.releasedDate())
                .status(Status.DRAFT)
                .build();

        Song saved = repository.save(song);
        yearService.create(songRecord.releaseYear());
        artistService.addArtistAndYearRelationship(artistId,songRecord.releaseYear(),
                saved.getId(),songRecord.genreId());

        return saved;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void userLikeASong(String songId, String userId) {

        repository.userLikeASong(songId,userId, LocalDateTime.now());

    }

    @Override
    public void userDisLikeASong(String songId, String userId) {
        repository.userDisLikeASong(songId,userId);

    }
}
