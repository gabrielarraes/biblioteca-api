package com.iesb.apibiblioteca.repository;

import com.iesb.apibiblioteca.model.book.Book;
import com.iesb.apibiblioteca.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
