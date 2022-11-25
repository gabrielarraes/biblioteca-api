package com.iesb.apibiblioteca.service.implementations.book;

import com.iesb.apibiblioteca.dto.book.BookDTO;
import com.iesb.apibiblioteca.exception.ResourceNotFoundException;
import com.iesb.apibiblioteca.model.book.Book;
import com.iesb.apibiblioteca.model.builder.book.BookBuilder;
import com.iesb.apibiblioteca.model.security.User;
import com.iesb.apibiblioteca.payload.request.book.RentRequest;
import com.iesb.apibiblioteca.repository.BookRepository;
import com.iesb.apibiblioteca.repository.UserRepository;
import com.iesb.apibiblioteca.service.book.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public BookServiceImpl(BookRepository bookRepo, UserRepository userRepo) {
       this.bookRepo = bookRepo;
       this.userRepo = userRepo;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookBuilder.builder()
                .withName(bookDTO.getName())
                .withUser(null)
                .withAuthor(bookDTO.getAuthor())
                .withLanguage(bookDTO.getLanguage())
                .withPages(bookDTO.getPages())
                .withRented(false)
                .withPublishingCompany(bookDTO.getPublishingCompany())
                .build();
        bookRepo.save(book);
        return bookDTO;
    }

    @Override
    public BookDTO updateBook(Long id,BookDTO bookDTO) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book Not Found."));

        if(book.getUser() != null) {
            User u = userRepo.findByUsername(book.getUser().getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid User"));
            book.setUser(u);
        } else {
            book.setUser(null);
        }

        book.setPublishingCompany(bookDTO.getPublishingCompany());
        book.setLanguage(bookDTO.getLanguage());
        book.setAuthor(bookDTO.getAuthor());
        book.setPages(bookDTO.getPages());
        book.setName(bookDTO.getName());
        book.setIsRented(bookDTO.getIsRented());

        bookRepo.save(book);

        return bookDTO;
    }

    @Override
    public String deleteBook(Long bookId) {
        bookRepo.deleteById(bookId);
        return "Book deleted.";
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();

        return convertList(books);
    }

    @Override
    public List<BookDTO> getAllBooksByCategoryId(Long id) {
        List<Book> books = bookRepo.findAllByCategoryId(id);

        return convertList(books);
    }

    @Override
    public List<BookDTO> getAllBooksByCategoryName(String name) {
        List<Book> books = bookRepo.findAllByCategoryName(name);

        return convertList(books);
    }

    @Override
    public BookDTO rentBook(RentRequest request) {
        Book b = bookRepo.getById(request.getBookId());
        User us = userRepo.getById(request.getUserId());

        b.setUser(us);
        b.setIsRented(true);

        bookRepo.save(b);

        return new BookDTO(b);
    }

    @Override
    public List<BookDTO> getAllUserBooks(Long userId) {
        return convertList(bookRepo.findAllByUserId(userId));
    }

    private List<BookDTO> convertList(List<Book> books) {
        List<BookDTO> booksDto = new ArrayList<>();

        if(!books.isEmpty()) {
            for(Book b : books) {
                booksDto.add(new BookDTO(b));
            }
        }
        return booksDto;
    }


}
