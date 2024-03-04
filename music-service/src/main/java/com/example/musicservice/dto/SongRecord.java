package com.example.musicservice.dto;

import com.example.musicservice.model.SongType;
import com.example.musicservice.model.StorageType;

import java.time.LocalDateTime;

public record SongRecord (String title,
                          String storageId,
                          StorageType storageType,
                          SongType songType,
                          String albumId,
                          String genreId,
                          long duration,
                          LocalDateTime releasedDate,
                          Integer releaseYear) {
}
