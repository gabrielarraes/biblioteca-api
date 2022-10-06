package com.iesb.apibiblioteca.service.security;

import com.iesb.apibiblioteca.dto.user.UserDTO;
import com.iesb.apibiblioteca.dto.user.UserDTOResponse;
import com.iesb.apibiblioteca.exception.AlreadyExistsException;
import com.iesb.apibiblioteca.exception.ResourceNotFoundException;
import com.iesb.apibiblioteca.payload.request.user.ResetPasswordRequest;
import com.iesb.apibiblioteca.payload.response.generic.GenericResponse;


public interface UserService {
    UserDTOResponse save(UserDTO userDTO) throws AlreadyExistsException;
    GenericResponse updateUserPassword(ResetPasswordRequest request) throws ResourceNotFoundException;
}

