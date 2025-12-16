package my.learn.mireaffjpractice12.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice12.entity.UserAuth;
import my.learn.mireaffjpractice12.model.TokenType;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtils {


    public String generateToken(UserAuth userDetails,
                                Date issuedAt,
                                Date expiresAt,
                                TokenType tokenType,
                                SecretKey secretKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("iat", issuedAt.getTime());
        claims.put("exp", expiresAt.getTime());
        claims.put("ath", userDetails.getAuthorities());
        claims.put("sid", userDetails.getId());
        claims.put("type", tokenType.toString());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims)
                .issuedAt(issuedAt)
                .expiration(expiresAt)
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaimsFromToken(String token,
                                      SecretKey secretKey) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();


    }

    public String getUsernameFromToken(String token,
                                       SecretKey secretKey) {
        return getClaimsFromToken(token, secretKey).getSubject();
    }

    public List<String> getAuthoritiesFromToken(String token,
                                                SecretKey secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return claims.get("ath", List.class);
    }

    public TokenType getTokenTypeFromToken(String token,
                                           SecretKey secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return TokenType.valueOf((String) claims.get("type"));
    }

    public Long getUserIdFromToken(String token,
                                   SecretKey secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return claims.get("sid", Long.class);
    }





}
