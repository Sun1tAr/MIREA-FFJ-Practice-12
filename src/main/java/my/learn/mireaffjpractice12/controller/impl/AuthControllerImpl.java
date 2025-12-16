package my.learn.mireaffjpractice12.controller.impl;

import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice12.DTO.request.LoginUserRequest;
import my.learn.mireaffjpractice12.DTO.request.RefreshTokenRequest;
import my.learn.mireaffjpractice12.DTO.request.RegisterUserRequest;
import my.learn.mireaffjpractice12.DTO.response.AuthResponse;
import my.learn.mireaffjpractice12.controller.AuthController;
import my.learn.mireaffjpractice12.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;


    @Override
    public ResponseEntity<AuthResponse> registerUser(RegisterUserRequest req) {
        return new ResponseEntity<>(authService.registerUser(req), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthResponse> loginUser(LoginUserRequest req) {
        return new ResponseEntity<>(authService.loginUser(req), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthResponse> logoutUser() {
        return new ResponseEntity<>(authService.logoutUser(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthResponse> refreshToken(RefreshTokenRequest req) {
        return new ResponseEntity<>(authService.refreshExpiredAccessToken(req), HttpStatus.OK);
    }
}
