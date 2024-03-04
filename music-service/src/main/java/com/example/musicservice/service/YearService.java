package com.example.musicservice.service;

import com.example.musicservice.model.Year;

public interface YearService {

    Year create(Integer year);
    Year getById(Integer year);
}
