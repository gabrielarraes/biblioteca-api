package com.iesb.apibiblioteca.dto.controller.user;

import com.iesb.apibiblioteca.dto.user.UserDTO;
import com.iesb.apibiblioteca.dto.user.UserDTOResponse;
import com.iesb.apibiblioteca.service.security.UserService;
import com.iesb.apibiblioteca.service.implementations.securityImpl.CustomUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService customUserService;

    public UserController(CustomUserServiceImpl customUserService) {
        this.customUserService = customUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTO userDTO) {
        UserDTOResponse u = customUserService.save(userDTO);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

}
