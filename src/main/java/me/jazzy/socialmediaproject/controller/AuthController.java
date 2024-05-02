package me.jazzy.socialmediaproject.controller;

import lombok.AllArgsConstructor;
import me.jazzy.socialmediaproject.request.LoginRequest;
import me.jazzy.socialmediaproject.request.RegisterRequest;
import me.jazzy.socialmediaproject.response.AuthResponse;
import me.jazzy.socialmediaproject.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authService.createUser(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}