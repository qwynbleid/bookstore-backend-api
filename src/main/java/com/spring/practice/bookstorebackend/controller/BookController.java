package com.spring.practice.bookstorebackend.controller;

import com.spring.practice.bookstorebackend.entity.Book;
import com.spring.practice.bookstorebackend.entity.User;
import com.spring.practice.bookstorebackend.repository.BookRepository;
import com.spring.practice.bookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all-books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/get-book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/add-book")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PostMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Deleted book with id: " + id);
        } else {
            return ResponseEntity.ok("Book with id: " + id + " not found");
        }
    }

    @PostMapping("/add-to-favorites/{userId}/{bookId}")
    public ResponseEntity<String> addToFavorites(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalUser.isPresent() && optionalBook.isPresent()) {
            User user = optionalUser.get();
            Book book = optionalBook.get();

            if (!user.getFavoriteBooks().contains(book)) {
                user.getFavoriteBooks().add(book);
                userRepository.save(user);
                return ResponseEntity.ok("Book added to favorites for user with id: " + userId);
            } else {
                return ResponseEntity.ok("Book is already in favorites for user with id: " + userId);
            }
        } else {
            return ResponseEntity.ok("User or Book not found");
        }
    }

    @PostMapping("/remove-from-favorites/{userId}/{bookId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Long userId, @PathVariable Long bookId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalUser.isPresent() && optionalBook.isPresent()) {
            User user = optionalUser.get();
            Book book = optionalBook.get();

            if (user.getFavoriteBooks().contains(book)) {
                user.getFavoriteBooks().remove(book);
                userRepository.save(user);
                return ResponseEntity.ok("Book removed from favorites for user with id: " + userId);
            } else {
                return ResponseEntity.ok("Book is not in favorites for user with id: " + userId);
            }
        } else {
            return ResponseEntity.ok("User or Book not found");
        }
    }


}
