package com.iesb.apibiblioteca.service.implementations.securityImpl;

import com.iesb.apibiblioteca.dto.user.UserDTO;
import com.iesb.apibiblioteca.dto.user.UserDTOResponse;
import com.iesb.apibiblioteca.exception.AlreadyExistsException;
import com.iesb.apibiblioteca.exception.ResourceNotFoundException;
import com.iesb.apibiblioteca.model.security.Role;
import com.iesb.apibiblioteca.model.security.User;
import com.iesb.apibiblioteca.payload.request.user.ResetPasswordRequest;
import com.iesb.apibiblioteca.payload.response.generic.GenericResponse;
import com.iesb.apibiblioteca.repository.RoleRepository;
import com.iesb.apibiblioteca.repository.UserRepository;
import com.iesb.apibiblioteca.service.security.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomUserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOResponse save(UserDTO userDTO) throws AlreadyExistsException {
        User u = new User();

        if(userRepo.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new AlreadyExistsException("User with Username: " + "'" + userDTO.getUsername() + "'" + " Already Exists");
        } else if(userRepo.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User with Email: " + "'" + userDTO.getEmail() + "'" + " Already Exists");
        }

        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setRoles(setDefaultRole());
        userRepo.save(u);

        UserDTOResponse uDTO = new UserDTOResponse();
        uDTO.setUsername(u.getUsername());
        uDTO.setEmail(u.getEmail());
        uDTO.setId(u.getId());

        return uDTO;

    }

    @Override
    public GenericResponse updateUserPassword(ResetPasswordRequest request) {
        User u = userRepo.findByEmail(request.getEmail()).orElseThrow(ResourceNotFoundException::new);

        u.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(u);

        return new GenericResponse("Senha Alterada com sucesso!");
    }

    public Set<Role> setDefaultRole() {
        Optional<Role> role = roleRepo.findById(1L);
        Set<Role> roles = new HashSet<>();

        if(role.isPresent()) {
            Role r = new Role();
            r.setName(role.get().getName());
            r.setId(role.get().getId());
            roles.add(r);
        }
        return roles;
    }
}
