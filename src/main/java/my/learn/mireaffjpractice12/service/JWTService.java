package my.learn.mireaffjpractice12.service;

import jakarta.validation.constraints.NotNull;
import my.learn.mireaffjpractice12.DTO.service.JWTokenOwner;
import my.learn.mireaffjpractice12.entity.UserAuth;
import my.learn.mireaffjpractice12.model.JWToken;

public interface JWTService {

    JWToken generateAccessTokenFor(UserAuth user);
    JWToken generateRefreshTokenFor(UserAuth user);
    JWTokenOwner getJWTOwner(String token);

    boolean isValidRefreshToken(@NotNull String refreshToken);

    void deleteRefreshTokenFor(UserAuth userAuth);
}
