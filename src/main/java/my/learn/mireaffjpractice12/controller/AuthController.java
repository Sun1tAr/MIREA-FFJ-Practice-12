package my.learn.mireaffjpractice12.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import my.learn.mireaffjpractice12.DTO.request.LoginUserRequest;
import my.learn.mireaffjpractice12.DTO.request.RefreshTokenRequest;
import my.learn.mireaffjpractice12.DTO.request.RegisterUserRequest;
import my.learn.mireaffjpractice12.DTO.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Ururur")
public interface AuthController {

    @PostMapping("/register")
    ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterUserRequest req);

    @PostMapping("/login")
    ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginUserRequest req);

    @PostMapping("/logout")
    ResponseEntity<AuthResponse> logoutUser();

    @PostMapping("/refresh")
    ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest req);

}
