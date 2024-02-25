package com.spring.practice.bookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.practice.bookstorebackend.dto.UserDTO;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("books")
    private List<Genre> genres;
    @ManyToMany(mappedBy = "favoriteBooks")
    @JsonIgnoreProperties("favoriteBooks")
    private List<User> usersWhoFavorited;

    public Book() {
    }

    public Book(Long id, String name, List<Author> authors, List<Genre> genres, List<User> usersWhoFavorited) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.genres = genres;
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<UserDTO> getUsersWhoFavorited() {
        if (usersWhoFavorited == null) {
            return Collections.emptyList();
        }

        return usersWhoFavorited.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }

    public void setUsersWhoFavorited(List<User> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }
}
