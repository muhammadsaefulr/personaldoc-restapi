package com.msaepulapp.personaldocapi.controller;

import com.msaepulapp.personaldocapi.entity.UserEntity;
import com.msaepulapp.personaldocapi.model.UpdateUserRequest;
import com.msaepulapp.personaldocapi.model.UserRegisterRequest;
import com.msaepulapp.personaldocapi.model.UserResponse;
import com.msaepulapp.personaldocapi.model.WebResponse;
import com.msaepulapp.personaldocapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/users/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody UserRegisterRequest request){
        userService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> getuser(UserEntity user){
        UserResponse userResponse = userService.get(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PutMapping(
            path = "/users/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(UserEntity user,UpdateUserRequest request){
        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }
}
