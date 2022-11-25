package com.iesb.apibiblioteca.service.implementations.category;

import com.iesb.apibiblioteca.dto.category.CategoryDTO;
import com.iesb.apibiblioteca.exception.ResourceNotFoundException;
import com.iesb.apibiblioteca.model.category.Category;
import com.iesb.apibiblioteca.repository.CategoryRepository;

import com.iesb.apibiblioteca.service.category.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> ct = categoryRepository.findAll();
        return convertList(ct);
    }

    @Override
    public String delete(Long id) {
        return "";
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category c = new Category();
        c.setName(categoryDTO.getName());

        categoryRepository.save(c);

        return categoryDTO;
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category c = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found."));

        c.setName(categoryDTO.getName());

        categoryRepository.save(c);

        return categoryDTO;
    }

    private List<CategoryDTO> convertList(List<Category> c) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        if(!c.isEmpty()) {
            for(Category cs : c) {
                categoryDTOS.add(new CategoryDTO(cs));
            }
        }
        return categoryDTOS;
    }
}
