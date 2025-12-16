package my.learn.mireaffjpractice12.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.time.Duration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Getter
    @Value("${jwt.lifetime.access}")
    private Duration accessTokenLifetime;

    @Getter
    @Value("${jwt.lifetime.refresh}")
    private Duration refreshTokenLifetime;

    @Bean
    public SecretKey secretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
