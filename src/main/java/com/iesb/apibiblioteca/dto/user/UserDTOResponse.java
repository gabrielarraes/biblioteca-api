package com.iesb.apibiblioteca.dto.user;

import com.iesb.apibiblioteca.model.book.Book;
import com.iesb.apibiblioteca.model.security.User;

public class UserDTOResponse extends BaseUserDTO {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
