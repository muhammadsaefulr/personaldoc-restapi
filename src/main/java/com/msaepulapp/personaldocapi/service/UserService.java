package com.msaepulapp.personaldocapi.service;

import com.msaepulapp.personaldocapi.model.UserRegisterRequest;
import com.msaepulapp.personaldocapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if(userRepository.existsById(request.getUsername()));
    }
}
