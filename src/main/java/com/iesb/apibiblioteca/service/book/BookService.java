package com.iesb.apibiblioteca.service.book;

import com.iesb.apibiblioteca.dto.book.BookDTO;
import com.iesb.apibiblioteca.payload.request.book.RentRequest;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id,BookDTO bookDTO);
    String deleteBook(Long bookId);
    List<BookDTO> getAllBooks();
    List<BookDTO> getAllBooksByCategoryId(Long id);
    List<BookDTO> getAllBooksByCategoryName(String name);
    BookDTO rentBook(RentRequest request);
    List<BookDTO> getAllUserBooks(Long userId);
}
