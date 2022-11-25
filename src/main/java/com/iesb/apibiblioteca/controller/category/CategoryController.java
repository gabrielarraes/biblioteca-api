package com.iesb.apibiblioteca.controller.category;

import com.iesb.apibiblioteca.dto.book.BookDTO;
import com.iesb.apibiblioteca.dto.category.CategoryDTO;
import com.iesb.apibiblioteca.service.book.BookService;
import com.iesb.apibiblioteca.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.getAll();
        return new ResponseEntity<>(categoryDTOS, (categoryDTOS.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK));
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategorie(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.create(categoryDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategorie(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.update(id, categoryDTO), HttpStatus.OK);
    }
}
