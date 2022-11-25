package com.iesb.apibiblioteca.service.category;

import com.iesb.apibiblioteca.dto.category.CategoryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryService {
    List<CategoryDTO> getAll();
    String delete(Long id);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(Long id, CategoryDTO categoryDTO);
}
