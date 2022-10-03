package com.iesb.apibiblioteca.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iesb.apibiblioteca.dto.user.UserDTO;
import com.iesb.apibiblioteca.model.book.Book;
import com.iesb.apibiblioteca.model.security.User;
import org.springframework.lang.Nullable;

import java.io.Serializable;

public class BookDTO implements Serializable {

    private Long pages;
    private String name;
    private Boolean isRented;
    private String language;
    private String author;
    private String publishingCompany;

    @Nullable
    private UserDTO user;

    public BookDTO() { }

    public BookDTO(final Book book) {
        pages = book.getPages();
        name = book.getName();
        isRented = book.getIsRented();
        language = book.getLanguage();
        author = book.getAuthor();
        publishingCompany = book.getPublishingCompany();
        user = book.getUser() != null ? buildUserDTO(book.getUser()) : null;
    }

    private UserDTO buildUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean rented) {
        isRented = rented;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
