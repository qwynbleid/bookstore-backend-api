package com.spring.practice.bookstorebackend.repository;

import com.spring.practice.bookstorebackend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
