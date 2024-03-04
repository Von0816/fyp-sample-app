package com.example.musicservice.repository;

import com.example.musicservice.model.User;
import com.example.musicservice.model.Year;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface YearRepository extends Neo4jRepository<Year,String> {

    Optional<Year>  findByYear(Integer year);
}
