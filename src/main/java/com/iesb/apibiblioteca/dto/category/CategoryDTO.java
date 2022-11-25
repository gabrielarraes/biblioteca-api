package com.iesb.apibiblioteca.dto.category;
import com.iesb.apibiblioteca.model.category.Category;

import java.io.Serializable;


public class CategoryDTO implements Serializable {

    private String name;
    private Long id;

    public CategoryDTO() { }

    public CategoryDTO(final Category c) {
        name = c.getName();
        id = c.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
