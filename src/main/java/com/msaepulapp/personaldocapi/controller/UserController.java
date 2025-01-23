package com.msaepulapp.personaldocapi.controller;

import com.msaepulapp.personaldocapi.entity.UserEntity;
import com.msaepulapp.personaldocapi.model.*;
import com.msaepulapp.personaldocapi.service.AuthService;
import com.msaepulapp.personaldocapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody UserRegisterRequest request){
        userService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody UserLoginRequest request){
        TokenResponse tokenResponse = authService.authLogin(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

    @Operation(summary = "Get current user", security = @SecurityRequirement(name = "X-API-TOKEN"))
    @GetMapping(
            path = "/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public WebResponse<UserResponse> get(@Parameter(hidden = true) UserEntity user){
        UserResponse userResponse = userService.get(user);
        log.info("User in controller: {}", user.getUsername());
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PutMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(@Parameter(hidden = true) UserEntity user,@RequestBody UpdateUserRequest request){
        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }
}
