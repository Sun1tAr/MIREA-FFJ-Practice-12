package my.learn.mireaffjpractice12.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice12.DTO.request.LoginUserRequest;
import my.learn.mireaffjpractice12.DTO.request.RefreshTokenRequest;
import my.learn.mireaffjpractice12.DTO.request.RegisterUserRequest;
import my.learn.mireaffjpractice12.DTO.response.AuthResponse;
import my.learn.mireaffjpractice12.DTO.service.JWTokenOwner;
import my.learn.mireaffjpractice12.entity.User;
import my.learn.mireaffjpractice12.entity.UserAuth;
import my.learn.mireaffjpractice12.exception.UnauthorizedException;
import my.learn.mireaffjpractice12.model.JWToken;
import my.learn.mireaffjpractice12.model.UserRole;
import my.learn.mireaffjpractice12.repository.AuthRepository;
import my.learn.mireaffjpractice12.service.AuthService;
import my.learn.mireaffjpractice12.service.JWTService;
import my.learn.mireaffjpractice12.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    public AuthResponse registerUser(RegisterUserRequest req) {

        User user = userService.save(User.getDefault());

        UserAuth userAuth = UserAuth.builder()
                .user(user)
                .authorities(List.of(UserRole.USER))
                .username(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        UserAuth saved = authRepository.save(userAuth);
        JWToken accessToken = jwtService.generateAccessTokenFor(saved);
        JWToken refreshToken = jwtService.generateRefreshTokenFor(saved);
        AuthResponse auth = AuthResponse.builder()
                .tokens(List.of(accessToken, refreshToken))
                .build();
        return auth;
    }

    @Override
    public AuthResponse loginUser(LoginUserRequest req) {
        UserAuth userAuth = loadUserByUsername(req.getEmail());

        if (!passwordEncoder.matches(req.getPassword(), userAuth.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        JWToken accessToken = jwtService.generateAccessTokenFor(userAuth);
        JWToken refreshToken = jwtService.generateRefreshTokenFor(userAuth);
        return  AuthResponse.builder()
                .tokens(List.of(accessToken, refreshToken))
                .build();
    }

    @Override
    public AuthResponse logoutUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserAuth)) {
            throw new UnauthorizedException("Invalid authentication. Please, repeat login");
        }
        UserAuth userAuth = (UserAuth) principal;

        jwtService.deleteRefreshTokenFor(userAuth);

        authentication.setAuthenticated(false);
        return AuthResponse.builder().build();
    }

    @Override
    public AuthResponse refreshExpiredAccessToken(RefreshTokenRequest req) {

        if (!jwtService.isValidRefreshToken(req.getRefreshToken())) {
            throw new UnauthorizedException("Invalid refresh token");
        }

        JWTokenOwner jwtOwner = jwtService.getJWTOwner(req.getRefreshToken());
        UserAuth userAuth;
        try {
            userAuth = authRepository.findById(jwtOwner.getUserId()).orElseThrow();
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid refresh token");
        }
        JWToken refresh = jwtService.generateRefreshTokenFor(userAuth);
        JWToken access = jwtService.generateAccessTokenFor(userAuth);
        return AuthResponse.builder()
                .tokens(List.of(access, refresh)).build();
    }

    @Override
    public Authentication createAuthByAccessJwt(String jwt) {

        JWTokenOwner owner = jwtService.getJWTOwner(jwt);
        UserAuth userAuth = loadUserByUsername(owner.getUsername());

        return new UsernamePasswordAuthenticationToken(
                userAuth,
                null,
                userAuth.getAuthorities()
        );
    }


    @Override
    public UserAuth loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth;
        try {
            userAuth = authRepository.findByUsername(username).orElseThrow();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return userAuth;
    }
}
