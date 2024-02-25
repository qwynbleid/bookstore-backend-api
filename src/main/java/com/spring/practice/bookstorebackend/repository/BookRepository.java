package com.spring.practice.bookstorebackend.repository;

import com.spring.practice.bookstorebackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
