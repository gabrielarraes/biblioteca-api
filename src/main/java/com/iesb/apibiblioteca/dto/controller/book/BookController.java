package com.iesb.apibiblioteca.dto.controller.book;

import com.iesb.apibiblioteca.dto.book.BookDTO;
import com.iesb.apibiblioteca.service.book.BookService;
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
}
