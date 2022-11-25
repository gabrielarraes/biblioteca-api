package com.iesb.apibiblioteca.repository;

import com.iesb.apibiblioteca.model.book.Book;
import com.iesb.apibiblioteca.model.category.Category;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value =
            "SELECT distinct id, name, pages, rented, author, publishing_company, language, user_id FROM Book " +
                    "JOIN book_categories c where c.category_id = ?;",  nativeQuery = true)
    List<Book> findAllByCategoryId(Long id);

    @Query(value =
            "SELECT distinct b.id, b.name, b.pages, b.rented, b.author, b.publishing_company, b.language, b.user_id FROM Book b JOIN category ct on ct.name = 'Matemática' JOIN book_categories c where ct.id = c.category_id;",
            nativeQuery = true)
    List<Book> findAllByCategoryName(String name);

    List<Book> findAllByUserId(Long id);
}
