package com.spring.practice.bookstorebackend.repository;

import com.spring.practice.bookstorebackend.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
