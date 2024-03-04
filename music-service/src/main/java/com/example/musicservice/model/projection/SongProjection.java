package com.example.musicservice.model.projection;

import com.example.musicservice.model.Year;

import java.time.LocalDate;

public interface SongProjection {
    String getId();
    String getDescription();
    String getName();
    String getStatus();
    String getDuration();
    String getStorageId();
    String getStorageType();
    String getType();
    Year getYear();


}
