package com.iesb.apibiblioteca.controller.book;

import com.iesb.apibiblioteca.dto.book.BookDTO;
import com.iesb.apibiblioteca.payload.request.book.BookCategoryFilter;
import com.iesb.apibiblioteca.payload.request.book.RentRequest;
import com.iesb.apibiblioteca.service.book.BookService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/book")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, (books.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK));
    }

    @PostMapping("/create")
    public ResponseEntity<BookDTO> getAllBooks(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> getAllBooks(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> getAllBooks(@PathVariable Long id, @RequestBody BookDTO bookDto) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDto), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<BookDTO>> getAllBooksByCategory(@PathVariable Long id) {
        List<BookDTO> books = bookService.getAllBooksByCategoryId(id);
        return new ResponseEntity<>(books, (books.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK));
    }

    @PostMapping("/category")
    public ResponseEntity<List<BookDTO>> getAllBooksByCategory(@RequestBody BookCategoryFilter filter) {
        List<BookDTO> books = bookService.getAllBooksByCategoryName(filter.getCategoryName());
        return new ResponseEntity<>(books, (books.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK));
    }

    @PostMapping("/rent")
    public ResponseEntity<BookDTO> rentBook(@RequestBody RentRequest request) {
        return new ResponseEntity<>(bookService.rentBook(request), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookDTO>> getAllUserBooks(@PathVariable Long id) {
        List<BookDTO> books = bookService.getAllUserBooks(id);
        return new ResponseEntity<>(books, (books.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK));
    }
}
