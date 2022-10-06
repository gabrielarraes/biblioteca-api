package com.iesb.apibiblioteca.controller.user;

import com.iesb.apibiblioteca.dto.user.UserDTO;
import com.iesb.apibiblioteca.dto.user.UserDTOResponse;
import com.iesb.apibiblioteca.payload.request.user.ForgotPasswordRequest;
import com.iesb.apibiblioteca.payload.request.user.ResetPasswordRequest;
import com.iesb.apibiblioteca.payload.response.generic.GenericResponse;
import com.iesb.apibiblioteca.service.email.EmailService;
import com.iesb.apibiblioteca.service.security.UserService;
import com.iesb.apibiblioteca.service.implementations.securityImpl.CustomUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService customUserService;
    private final EmailService emailService;

    public UserController(CustomUserServiceImpl customUserService, EmailService emailService) {
        this.customUserService = customUserService;
        this.emailService = emailService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTO userDTO) {
        UserDTOResponse u = customUserService.save(userDTO);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> sendEmail(@RequestBody ForgotPasswordRequest request) {
        this.emailService.sendEmail(request.getEmail());
        return new ResponseEntity<>("email enviado para: " + request.getEmail(), HttpStatus.OK);
    }

    @PostMapping("/passwordRedefinition")
    public ResponseEntity<GenericResponse> changePassword(@RequestBody ResetPasswordRequest request) {
        return new ResponseEntity<>(customUserService.updateUserPassword(request), HttpStatus.OK);
    }

}
