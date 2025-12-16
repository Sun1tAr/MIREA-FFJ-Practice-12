package my.learn.mireaffjpractice12.service.impl;

import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice12.DTO.service.JWTokenOwner;
import my.learn.mireaffjpractice12.config.JwtConfig;
import my.learn.mireaffjpractice12.entity.UserAuth;
import my.learn.mireaffjpractice12.model.JWToken;
import my.learn.mireaffjpractice12.model.TokenType;
import my.learn.mireaffjpractice12.service.JWTService;
import my.learn.mireaffjpractice12.util.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final JwtUtils jwtUtils;
    private final JwtConfig  jwtConfig;
    private final SecretKey secretKey;
    private final RedisTemplate<Long, String> redisTemplate;


    @Override
    public JWToken generateAccessTokenFor(UserAuth user) {
        TokenType type = TokenType.ACCESS_BEARER;
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + jwtConfig.getAccessTokenLifetime().toMillis());
        String payload = jwtUtils.generateToken(
                user,
                issuedAt,
                expiresAt,
                type,
                secretKey);
        return JWToken.builder()
                .tokenType(type)
                .token(payload)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .build();
    }

    @Override
    public JWToken generateRefreshTokenFor(UserAuth user) {
        TokenType type = TokenType.REFRESH_BEARER;
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + jwtConfig.getRefreshTokenLifetime().toMillis());
        String payload = jwtUtils.generateToken(
                user,
                issuedAt,
                expiresAt,
                type,
                secretKey);

        redisTemplate.opsForValue().set(user.getId(), payload);

        return JWToken.builder()
                .tokenType(type)
                .token(payload)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .build();
    }

    @Override
    public JWTokenOwner getJWTOwner(String token) {
        Long id = jwtUtils.getUserIdFromToken(token,  secretKey);
        String username = jwtUtils.getUsernameFromToken(token,  secretKey);
        return new JWTokenOwner(id, username);
    }

    @Override
    public boolean isValidRefreshToken(String refreshToken) {
        Long id;
        try {
            id = jwtUtils.getUserIdFromToken(refreshToken, secretKey);
        } catch (Exception e) {
            return false;
        }
        String saved = redisTemplate.opsForValue().get(id);
        return saved != null && saved.equals(refreshToken);
    }

    @Override
    public void deleteRefreshTokenFor(UserAuth userAuth) {
        redisTemplate.delete(userAuth.getId());
    }
}
