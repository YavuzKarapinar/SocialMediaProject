package me.jazzy.socialmediaproject.service;

import lombok.AllArgsConstructor;
import me.jazzy.socialmediaproject.jwt.JwtGenerator;
import me.jazzy.socialmediaproject.model.User;
import me.jazzy.socialmediaproject.model.Verification;
import me.jazzy.socialmediaproject.repository.UserRepository;
import me.jazzy.socialmediaproject.request.LoginRequest;
import me.jazzy.socialmediaproject.request.RegisterRequest;
import me.jazzy.socialmediaproject.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthResponse createUser(RegisterRequest registerRequest) {
        System.out.println("Created User kısmına girdi");
        boolean isAlreadyUsed = userRepository.findByEmail(registerRequest.getEmail())
                .isPresent();

        if (isAlreadyUsed)
            throw new RuntimeException("There is already a user with that email.");

        LocalDateTime birthDate = LocalDateTime.parse(registerRequest.getBirthDate());

        User user = new User(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                birthDate,
                new Verification()
        );

        userService.saveUser(user);

        return new AuthResponse(
                "Successfully signed up.",
                "",
                HttpStatus.CREATED.value()
        );
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("There is no such email."));

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(),
                                loginRequest.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(user.getEmail());

        return new AuthResponse(
                "Successfully signed in.",
                token,
                HttpStatus.OK.value()
        );
    }
}