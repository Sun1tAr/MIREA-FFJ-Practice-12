package my.learn.mireaffjpractice12.exception;

import org.springframework.http.HttpStatus;

public class JWTException extends AppException{

    public JWTException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
