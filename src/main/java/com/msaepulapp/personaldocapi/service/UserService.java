package com.msaepulapp.personaldocapi.service;

import com.msaepulapp.personaldocapi.entity.UserEntity;
import com.msaepulapp.personaldocapi.model.UpdateUserRequest;
import com.msaepulapp.personaldocapi.model.UserRegisterRequest;
import com.msaepulapp.personaldocapi.model.UserResponse;
import com.msaepulapp.personaldocapi.repository.UserRepository;
import com.msaepulapp.personaldocapi.security.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(UserRegisterRequest request){
        validationService.validate(request);

        if(userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        };

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setDescription(request.getDescription());

        userRepository.save(user);
    }

    public UserResponse get(UserEntity user){
        log.info("Info Username: ",user.getUsername());
        return UserResponse.builder()
                .username(user.getUsername())
                .description(user.getDescription())
                .build();
    }

    @Transactional
    public UserResponse update(UserEntity user, UpdateUserRequest request){
        validationService.validate(request);

        if(!userRepository.existsById(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
        }

        log.info("Request: {}", request);

        if(Objects.nonNull(request.getUsername())){
            user.setUsername(request.getUsername());
        };

        if(Objects.nonNull(request.getDescription())){
            user.setDescription(request.getDescription());
        }

        if(Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(user);

        log.info("Save user: {}", user);

        return UserResponse.builder()
                .username(user.getUsername())
                .description(user.getDescription())
                .build();
    }
}
